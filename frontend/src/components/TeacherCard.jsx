import React from 'react'

const TeacherCard = ({firstName, lastName, birthYear, salary}) => {
  return (
    <div className='teacher-card'>
        <h2 className='text-2xl font-bold'>{firstName} {lastName}</h2>
        <p className='text-lg'>Birth Year: {birthYear}</p>
        <p className='text-lg'>Salary: {salary}$</p>
    </div>
  )
}

export default TeacherCard