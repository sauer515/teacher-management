import React from 'react'

const GroupCard = ({groupName, onClick}) => {
  return (
    <div className='group-card' onClick={onClick}>
        <h2 className='text-2xl font-bold'>{groupName}</h2>
    </div>
  )
}

export default GroupCard