import React, { useEffect, useState } from 'react';
import TeacherCard from '../components/TeacherCard';

function Teachers() {
  const [data, setData] = useState([]);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetch('http://localhost:8080/api/teacher')
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
     <h1>Teachers</h1>
        {error ? (
          <p>Error: {error.message}</p>
        ) : data.length > 0 ? (
          <ul className="data-list"> 
            {data.map((teacher, index) => (
              <TeacherCard key={index} {...teacher} />
            ))}
          </ul>
        ) : (
          <p>Loading...</p>
        )}
    </div>
  );
}

export default Teachers;
