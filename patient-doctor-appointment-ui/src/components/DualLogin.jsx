import React from 'react';
import DoctorLoginForm from './DoctorLoginForm';
import PatientLoginForm from './PatientLoginForm';

const DualLogin = () => {
  return (
    <div>
      <h2>Doctor Login</h2>
      <DoctorLoginForm />
      <h2>Patient Login</h2>
      <PatientLoginForm />
    </div>
  );
};

export default DualLogin;