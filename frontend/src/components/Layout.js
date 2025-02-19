import React from 'react'
import { Link } from 'react-router-dom'
import './layout.css'

const Layout = ({children}) => {
  return (
    <div className='layout'>
        <aside className='sidebar'>
            <nav>
                <ul>
                    <li><Link to='/'>Home</Link></li>
                    <li><Link to='/teachers'>Teachers</Link></li>
                    <li><Link to='/groups'>Teacher Groups</Link></li>
                </ul>
            </nav>
        </aside>
        <main className='content'>
            {children}
        </main>
    </div>
  )
}

export default Layout