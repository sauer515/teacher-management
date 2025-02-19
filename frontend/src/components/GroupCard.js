import React from 'react'

const GroupCard = ({groupName, groupMaxNumber, groupCurrentNumber}) => {
  return (
    <div className='group-card'>
        <h2 className='text-2xl font-bold'>{groupName}</h2>
        <p className='text-lg'>Max Number: {groupMaxNumber}</p>
        <p className='text-lg'>Current Number: {groupCurrentNumber}</p>
        <p className='text-lg'>Available Seats: {groupMaxNumber - groupCurrentNumber}</p>
    </div>
  )
}

export default GroupCard