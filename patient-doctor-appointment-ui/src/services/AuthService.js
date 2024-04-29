import axios from "axios";

const AUTH_REST_API_URL = "http://localhost:8080/api/auth";

export const doctorRegisterAPICall = (registerObj) =>
  axios.post(AUTH_REST_API_URL + "/doctor-register", registerObj);

export const doctorLoginAPICall = (username, password) =>
  axios.post(AUTH_REST_API_URL + "/doctor-login", { username, password });
  
export const patientRegisterAPICall = (registerObj) =>
  axios.post(AUTH_REST_API_URL + "/patient-register", registerObj);

export const patientLoginAPICall = (username, password) =>
  axios.post(AUTH_REST_API_URL + "/patient-login", { username, password });

export const storeToken = (token) => localStorage.setItem("token", token);

export const getToken = () => localStorage.getItem("token");

export const saveLoggedInUser = (username, role) => {
  sessionStorage.setItem("authenticatedUser", username);
  sessionStorage.setItem("role", role);
};

export const isUserLoggedIn = () => {
  const username = sessionStorage.getItem("authenticatedUser");
  if (username == null) {
    return false;
  } else {
    return true;
  }
};

export const getLoggedInUser = () => {
  return sessionStorage.getItem("authenticatedUser");
};

export const logout = () => {
  sessionStorage.clear();
  localStorage.clear();
};
export const isPatientUser = () => {
  const role = sessionStorage.getItem("role");

  if (role != null && role == "ROLE_PATIENT") {
    return true;
  } else {
    return false;
  }
};
export const isDoctorUser = () => {
  const role = sessionStorage.getItem("role");

  if (role != null && role == "ROLE_DOCTOR") {
    return true;
  } else {
    return false;
  }
};

export const isAdminUser = () => {
  const role = sessionStorage.getItem("role");

  if (role != null && role == "ROLE_ADMIN") {
    return true;
  } else {
    return false;
  }
};
