import React from "react";
import { useState } from "react";
import { doctorRegisterAPICall } from "../services/AuthService";
import { Navigate, useNavigate } from "react-router-dom";

const DoctorRegister = () => {
  const [name, setName] = useState("");
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [specialization,setSpecialization] = useState("");
  const [successMessage, setSuccessMessage] = useState('');

  const navigate = useNavigate();
  

  async function handleRegistrationForm(e) {
    e.preventDefault();

    const register = { name, username, email, password, specialization };

    try {
      const response = await doctorRegisterAPICall(register);
      setSuccessMessage('Doctor Registered successfully!');
      console.log(response.data);
    } catch (error) {
      console.error(error);
    }
    
  }
  const backToLogin =()=>{
    navigate("/")
  }

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3">
          <div className="card">
            <div className="card-header">
              <h2 className="text-center">Doctor Registration Form</h2>
            </div>
            <div className="card-body">
              <form>
                <div className="row mb-3">
                  <label className="col-md-3 control-label">Name</label>
                  <div className="col-md-9">
                    <input
                      type="text"
                      name="name"
                      className="form-control"
                      placeholder="Enter name"
                      value={name}
                      onChange={(e) => setName(e.target.value)}
                    />
                  </div>
                </div>
                <div className="row mb-3">
                  <label className="col-md-3 control-label">Username</label>
                  <div className="col-md-9">
                    <input
                      type="text"
                      name="username"
                      className="form-control"
                      placeholder="Enter User Name"
                      value={username}
                      onChange={(e) => setUsername(e.target.value)}
                    />
                  </div>
                </div>
                <div className="row mb-3">
                  <label className="col-md-3 control-label">Email</label>
                  <div className="col-md-9">
                    <input
                      type="email"
                      name="email"
                      className="form-control"
                      placeholder="Enter email"
                      value={email}
                      onChange={(e) => setEmail(e.target.value)}
                    />
                  </div>
                </div>
                <div className="row mb-3">
                  <label className="col-md-3 control-label">Password</label>
                  <div className="col-md-9">
                    <input
                      type="password"
                      name="password"
                      className="form-control"
                      placeholder="Enter password"
                      value={password}
                      onChange={(e) => setPassword(e.target.value)}
                    />
                  </div>
                </div>
                <div className="row mb-3">
                  <label className="col-md-3 control-label">Specialization</label>
                  <div className="col-md-9">
                    <input
                      type="text"
                      name="specialization"
                      className="form-control"
                      placeholder="Enter specialization"
                      value={specialization}
                      onChange={(e) => setSpecialization(e.target.value)}
                    />
                  </div>
                </div>
                
                <div className="form-group mb-3">
                  <button
                    className="btn btn-primary"
                    onClick={(e) => handleRegistrationForm(e)}
                  >
                    Submit
                  </button>
                </div>
                <div className="form-group mb-3">
                  <button
                  className="btn btn-primary"
                  onClick={backToLogin}>
                    Back to Login
                  </button>

                </div>
              </form>
              {successMessage && <div className="alert alert-success mt-3">{successMessage}</div>}
            </div>
          </div>
        </div>
      </div>
    </div>
    
  );
};

export default DoctorRegister;