import React from "react";
import { useEffect } from "react";
import { useState } from "react";
import { useNavigate } from "react-router";
import { isPatientUser } from "../../services/AuthService";
import { viewDoctors } from "../../services/ManageService";



const DoctorsList = () => {
  const [doctors, setDoctors] = useState([]);

  const navigate = useNavigate();

  const isPatient = isPatientUser();

  useEffect(() => {
    listDoctors();
  }, []);

  const listDoctors = async () => {
    try {
      const response = await viewDoctors();
      setDoctors(response.data);
    } catch (err) {
      console.error(error);
    }
  };

  function book() {
    navigate("/doctors/book");
  }


  return (
    <div className="container">
      <h2 className="text-center">List of Doctors</h2>
      <div>
      <button className="btn btn-info" onClick={book}>
        Book
        </button>
        <table className="table table-bordered table-striped">
          <thead>
            <tr>
              <th>Name</th>
              <th>Email</th>
              <th>Specialization</th>
            </tr>
          </thead>
          <tbody>
            {doctors.map((doctor) => (
              <tr key={doctor.id}>
                <td>{doctor.name}</td>
                <td>{doctor.email}</td>
                <td>{doctor.specialization}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default DoctorsList;
