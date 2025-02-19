import React, { useEffect, useState } from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Layout from './components/Layout'
//import Home from './pages/Home';
import Teachers from './pages/Teachers';
import Groups from './pages/TeacherGroups';
import './App.css';

function App() {
  return (
    <Router>
      <Layout>
        <Routes>
          {/* <Route path="/" element={<Home />} /> */}
          <Route path="/teachers" element={<Teachers />} />
          <Route path="/groups" element={<Groups />} /> 
        </Routes>
      </Layout>
    </Router>
  );
}

export default App;
