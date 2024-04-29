import React, { useState, useEffect } from 'react';
import { bookAppointment, viewDoctors } from '../../services/ManageService';


const BookingForm = () => {
  const [selectedDateTime, setSelectedDateTime] = useState('');
  const [selectedDoctor, setSelectedDoctor] = useState('');
  const [doctors, setDoctors] = useState([]);
  const [patientId, setPatientId] = useState('');
  const [successMessage, setSuccessMessage] = useState('');
   // Retrieve role name from session storage

  useEffect(() => {
    fetchDoctors();
  }, []);

  const fetchDoctors = async () => {
    try {
      const response = await viewDoctors();
      setDoctors(response.data);
    } catch (error) {
      console.error('Error fetching doctors:', error);
    }
  };

  const selectDate = selectedDateTime 

  
  useEffect(() => {
   handleSubmit();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    console.log(selectDate);
    console.log(selectedDoctor)
    console.log(patientId)
    const book = {
        appointmentDateTime: selectDate,
        doctorId: selectedDoctor,
        patientId,
      };
    try {
      await bookAppointment(book);
      setSuccessMessage('Appointment booked successfully!');
    } catch (error) {
      console.error('Error booking appointment:', error);
    }
  };
  const handleDateTimeChange = (e) => {
    setSelectedDateTime(e.target.value);
  };

  const handlePatientIdChange = (e) => {  
    setPatientId(e.target.value);
  };

  return (
    <div className="container">
      <h2>Book Appointment</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="appointmentDateTime">Appointment Date & Time</label>
          <input
            type="datetime-local"
            className="form-control"
            id="appointmentDateTime"
            value={selectedDateTime}
            onChange={handleDateTimeChange}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="doctorId">Doctor</label>
          <select
            className="form-control"
            id="doctorId"
            value={selectedDoctor}
            onChange={(e) => setSelectedDoctor(e.target.value)}
            required
          >
            <option value="">Select Doctor</option>
            {doctors.map((doctor) => (
              <option key={doctor.doctorId} value={doctor.doctorId}>
                {doctor.name}
              </option>
            ))}
          </select>
        </div>
        <div className='form-group'>
            <label htmlFor='patientId' >patientId</label>
            <input className='form-control' id='patientId' value={patientId} 
        onChange={handlePatientIdChange} ></input>
        </div>
        <button type="submit" className="btn btn-primary">Book Appointment</button>
      </form>
      {successMessage && <div className="alert alert-success mt-3">{successMessage}</div>}
    </div>
  );
};

export default BookingForm;