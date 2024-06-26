import React from "react";
import { useState } from "react";
import { patientRegisterAPICall } from "../services/AuthService";
import { useNavigate } from "react-router-dom";

const PatientRegister = () => {
  const [name, setName] = useState("");
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [gender,setGender] = useState("");
  const [contactNumber,setContactNumber] = useState("");
  const [successMessage, setSuccessMessage] = useState('');

  const navigate = useNavigate();
  async function handleRegistrationForm(e) {
    e.preventDefault();

    const register = { name, username, email, password, gender, contactNumber };

    try {
      const response = await patientRegisterAPICall(register);
      setSuccessMessage('Patient Registered successfully!');
      console.log(response.data);
    } catch (error) {
      console.error(error);
    }
  }
  const backToLogin=()=>{
    navigate("/")
  }

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3">
          <div className="card">
            <div className="card-header">
              <h2 className="text-center">Patient Registration Form</h2>
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
                  <label className="col-md-3 control-label">Gender</label>
                  <div className="col-md-9">
                    <input
                      type="text"
                      name="gender"
                      className="form-control"
                      placeholder="Enter gender"
                      value={gender}
                      onChange={(e) => setGender(e.target.value)}
                    />
                  </div>
                </div>
                <div className="row mb-3">
                  <label className="col-md-3 control-label">Contact</label>
                  <div className="col-md-9">
                    <input
                      type="text"
                      name="contactNumber"
                      className="form-control"
                      placeholder="Enter contact"
                      value={contactNumber}
                      onChange={(e) => setContactNumber(e.target.value)}
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
                <div>
                <button
                    className="btn btn-primary"
                    onClick={backToLogin}
                  >
                    BackToLogin
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

export default PatientRegister;
