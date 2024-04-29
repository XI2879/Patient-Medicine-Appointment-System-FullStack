// PatientLoginForm.jsx
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { patientLoginAPICall, saveLoggedInUser, storeToken } from '../services/AuthService';

const PatientLoginForm = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const navigate = useNavigate();
  async function handleSubmit(e) {
    e.preventDefault();
        try {
      const response = await patientLoginAPICall(username, password);
      const token = "Bearer " + response.data.accessToken;
      const role = response.data.role;

      storeToken(token);
      saveLoggedInUser(username, role);
      navigate("/logged-in");
    } catch (error) {
      console.error(error);
    }
    
    // Handle Patient login submission
  };

  return (
    <form onSubmit={handleSubmit}>
      <input type="text" placeholder="Username" value={username} onChange={(e) => setUsername(e.target.value)} />
      <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} />
      <button type="submit">Login</button>
    </form>
  );
};

export default PatientLoginForm;