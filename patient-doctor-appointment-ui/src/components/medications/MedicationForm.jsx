import React, { useState, useEffect } from 'react';
import { addMedication, viewPatients } from '../../services/ManageService';


const MedicationForm = () => {
  const [name, setName] = useState('');
  const [selectedPatient, setSelectedPatient] = useState('');
  const [patients, setPatients] = useState([]);
  const [medicationDetail, setMedicationDetail] = useState('');
  const [dosage, setDosage] = useState('');
  const [days,setDays] = useState();
  const [successMessage, setSuccessMessage] = useState('');
   // Retrieve role name from session storage

  useEffect(() => {
    fetchPatients();
  }, []);

  const fetchPatients = async () => {
    try {
      const response = await viewPatients();
      setPatients(response.data);
    } catch (error) {
      console.error('Error fetching doctors:', error);
    }
  };

  

  
  useEffect(() => {
   handleSubmit();
  }, []);

  const handleSubmit = async (e) => {
    
    e.preventDefault();
    console.log(selectedPatient);
    const medications = {
        name: name,
        medication:medicationDetail,
        dosage:dosage,
        days:days,
        patientId: selectedPatient
      };
    try {
      await addMedication(medications);
      setSuccessMessage('Medication added successfully!');
    } catch (error) {
      console.error('Error adding  medication:', error);
    }
  };
 

  return (
    <div className="container">
      <h2>Add Medication</h2>
      <form onSubmit={handleSubmit}>
      <div className="form-group">
        <label htmlFor="name">Name</label>
        <input
          type="text"
          className="form-control"
          id="name"
          name="name"
          value={name}
          onChange={(e)=>setName(e.target.value)}
          required
        />
      </div>
      <div className="form-group">
        <label htmlFor="medication">Medication </label>
        <input
          type="text"
          className="form-control"
          id="medication"
          name="medication"
          value={medicationDetail}
          onChange={(e)=>setMedicationDetail(e.target.value)}
          required
        />
      </div>
      <div className="form-group">
        <label htmlFor="dosage">Dosage</label>
        <input
          type="text"
          className="form-control"
          id="dosage"
          name="dosage"
          value={dosage}
          onChange={(e)=>setDosage(e.target.value)}
          required
        />
      </div>
      <div className="form-group">
        <label htmlFor="days">Number of Days</label>
        <input
          type="number"
          className="form-control"
          id="days"
          name="days"
          value={days}
          onChange={(e)=>setDays(e.target.value)}
          required
        />
      </div>
          
        <div className="form-group">
          <label htmlFor="patientId">Patient</label>
          <select
            className="form-control"
            id="patientd"
            value={selectedPatient}
            onChange={(e) => setSelectedPatient(e.target.value)}
            required
          >
            <option value="">Select Patient</option>
            {patients.map((patient) => (
              <option key={patient.patientId} value={patient.patientId}>
                {patient.name}
              </option>
            ))}
          </select>
        </div>
        <button type="submit" className="btn btn-primary">Add Medication</button>
      </form>
      {successMessage && <div className="alert alert-success mt-3">{successMessage}</div>}
    </div>
  );
};

export default MedicationForm;