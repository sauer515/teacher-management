import React, { useEffect } from 'react'
import { useState } from 'react'
import GroupCard from '../components/GroupCard';
import TeacherCard from '../components/TeacherCard';

const TeacherGroups = () => {
  const [teachers, setTeachers] = useState([]);
  const [error, setError] = useState(null);
  const [teacherGroups, setTeacherGroups] = useState([]);
  const [clickedGroup, setClickedGroup] = useState(null);

  useEffect(() => {
    fetch('http://localhost:8080/api/group')
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then(data => setTeacherGroups(data))
      .catch(error => setError(error));
  }, []);

  useEffect(() => {
    if (clickedGroup) {
        fetch(`http://localhost:8080/api/group/${clickedGroup}/teacher`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => setTeachers(data))
        .catch(error => setError(error));
        console.log(teachers);
    }
    }, [clickedGroup]);

    const handleGroupClick = (groupName) => {
        setClickedGroup(groupName);
    }

  return (
    <div>
      <h1>Teacher Groups</h1>
        {error ? (
          <p>Error: {error.message}</p>
        ) : teacherGroups ? (
          <ul className="data-list"> 
            {teacherGroups.map((group, index) => (
                <GroupCard key={index} groupName={group} onClick={() => handleGroupClick(group)}/>
            ))}
          </ul>
        ) : (
          <p>Loading...</p>
        )}
        {clickedGroup && (
            <>
            <div>
                <h2>Teachers in {clickedGroup} group:</h2>
                {teachers.length > 0 ? (
                <ul className="teacher-list">
                    {teachers.map((teacher, index) => (
                        <TeacherCard key={index} {...teacher} />
                    ))}
                </ul>
                ) : (<p>No teachers in this group</p>)
                }
            </div>
            <div className=''>
                <button onClick={() => setClickedGroup(null)}>Back</button>
                
            </div>
            </>
        )}
    </div>
  )
}

export default TeacherGroups