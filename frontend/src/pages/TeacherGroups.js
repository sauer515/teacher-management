import React, { useEffect } from 'react'
import { useState } from 'react'
import GroupCard from '../components/GroupCard';

const TeacherGroups = () => {
  const [data, setData] = useState([]);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetch('http://localhost:8080/api/group')
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then(data => setData(data))
      .catch(error => setError(error));
  }, []);

  return (
    <div>
      <h1>Teacher Groups</h1>
        {error ? (
          <p>Error: {error.message}</p>
        ) : data.length > 0 ? (
          <ul className="data-list"> 
            {data.map((group, index) => (
              <GroupCard key={index} {...group} />
            ))}
          </ul>
        ) : (
          <p>Loading...</p>
        )}
    </div>
  )
}

export default TeacherGroups