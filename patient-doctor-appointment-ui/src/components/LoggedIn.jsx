import React from 'react';
import { NavLink, useNavigate } from 'react-router-dom';
import { isAdminUser, isDoctorUser, isPatientUser, logout } from '../services/AuthService';



const LoggedIn = () => {
    const navigate = useNavigate();
    const isAdmin = isAdminUser();
    const isDoctor = isDoctorUser();
    const isPatient = isPatientUser();
    const handleLogout =(e) =>{
        logout();
        navigate("/");
    }
  return (
    <div className="container text-center mt-5" >
      <h1>Welcome to the HomePage</h1>
      <div className="btn-group-vertical mt-5" style={{ width: '100%' }}>
        {(isAdmin || isPatient) && (<NavLink
          to="/doctors"
          className="btn btn-primary btn-lg btn-block"
          activeClassName="active"
        >
          Doctors
        </NavLink> )}
        {(isAdmin || isDoctor)&& (<NavLink
          to="/appointments"
          className="btn btn-success btn-lg btn-block"
          activeClassName="active"
        >
          Appointments
        </NavLink>)}
        {(isAdmin || isDoctor) && (<NavLink
          to="/patients"
          className="btn btn-info btn-lg btn-block"
          activeClassName="active"
        >
          Patients
        </NavLink>)}
        {(isAdmin || isPatient)&&(<NavLink
          to="/medications"
          className="btn btn-warning btn-lg btn-block"
          activeClassName="active"
        >
          Medications
        </NavLink>)}
        
        <button className="btn btn-danger btn-lg btn-block mt-3" onClick={handleLogout}>Logout</button>
      </div>
    </div>
  );
};

export default LoggedIn;