import React from 'react';

function Header() {
  return (
    <header className="bg-gray-800 text-white p-4 shadow-md">
      <div className="container mx-auto flex justify-between items-center">
        {/* Logo */}
        <a href="/" className="text-2xl font-bold text-white hover:text-blue-400 transition duration-300">
          IcesiTrade
        </a>

        {/* Centralized Search Bar and Navigation - new wrapping div for centering */}
        <div className="flex-grow flex justify-center items-center px-4"> {/* Added px-4 for horizontal padding */}
          {/* Search Bar */}
          <div className="relative flex-grow max-w-2xl"> {/* flex-grow and max-w for larger bar */}
            <input
              type="text"
              placeholder="Search..."
              className="bg-gray-700 text-white placeholder-gray-400 py-3 pl-10 pr-4 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent transition duration-300 w-full" // w-full for input to fill parent
            />
            {/* Search Icon */}
            <svg
              className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400 h-5 w-5"
              fill="none"
              stroke="currentColor"
              viewBox="0 0 24 24"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                strokeWidth="2"
                d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"
              ></path>
            </svg>
            {/* Search Button (optional, to mimic Amazon) */}
            <button className="absolute right-0 top-0 h-full bg-orange-400 hover:bg-orange-500 rounded-r-md px-4 text-white font-semibold flex items-center justify-center">
              <svg className="h-5 w-5" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"></path>
              </svg>
            </button>
          </div>
        </div>

        {/* Navigation (aligned to the right) */}
        <nav>
          <ul className="flex space-x-6">
            <li>
              <a href="/about" className="text-white hover:text-blue-400 transition duration-300">
                About
              </a>
            </li>
            <li>
              <a href="/services" className="text-white hover:text-blue-400 transition duration-300">
                Services
              </a>
            </li>
            <li>
              <a href="/contact" className="text-white hover:text-blue-400 transition duration-300">
                Contact
              </a>
            </li>
          </ul>
        </nav>
      </div>
    </header>
  );
}

export default Header;