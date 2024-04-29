// DoctorLoginForm.jsx
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { doctorLoginAPICall, saveLoggedInUser, storeToken } from '../services/AuthService';

const DoctorLoginForm = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const navigate = useNavigate();

  async function handleSubmit(e) {
    e.preventDefault();
    try{
      const response = await doctorLoginAPICall(username, password);
      const token = "Bearer " + response.data.accessToken;
      const role = response.data.role;

      storeToken(token);
      saveLoggedInUser(username, role);
      navigate("/logged-in");
    }catch{
      console.error(console.error());
    }

    
    // Handle Doctor login submission
  };

  return (
    <form onSubmit={handleSubmit}>
      <input type="text" placeholder="Username" value={username} onChange={(e) => setUsername(e.target.value)} />
      <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} />
      <button type="submit">Login</button>
    </form>
  );
};

export default DoctorLoginForm;