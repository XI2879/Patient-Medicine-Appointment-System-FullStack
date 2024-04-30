import React from "react";
import { useEffect } from "react";
import { useState } from "react";
import { useNavigate } from "react-router";
import { isPatientUser } from "../../services/AuthService";
import {  viewPatients } from "../../services/ManageService";





const PatientsList = () => {
  const [patients, setPatients] = useState([]);

  const navigate = useNavigate();

  const isPatient = isPatientUser();

  useEffect(() => {
    listPatients();
  }, []);

  const listPatients = async () => {
    try {
      const response = await viewPatients();
      setPatients(response.data);
    } catch (err) {
      console.error(console.error());
    }
  };

  function medication() {
    navigate("/doctors/add-medication");
  }


  return (
    <div className="container">
      <h2 className="text-center">List of Patients</h2>
      <div>
      <button className="btn btn-info" onClick={medication}>
        Add Medication
        </button>
        <table className="table table-bordered table-striped">
          <thead>
            <tr>
              <th>Name</th>
              <th>Email</th>
              <th>Gender</th>
              <th>Contact</th>
            </tr>
          </thead>
          <tbody>
            {patients.map((patient) => (
              <tr key={patient.id}>
                <td>{patient.name}</td>
                <td>{patient.email}</td>
                <td>{patient.gender}</td>
                <td>{patient.contactNumber}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default PatientsList;
