import { useState } from 'react';
import { FaBell } from 'react-icons/fa';
import NotificationList from './NotificationList';


function NotificationBell({ userId }: { userId: string }) {
  const [show, setShow] = useState(false);

  return (
    <div className="relative">
      <button
        onClick={() => setShow(!show)}
        className="text-blue-700 hover:text-blue-900 text-2xl"
      >
        <FaBell />
      </button>
      {show && <NotificationList userId={userId} />}
    </div>
  );
}

export default NotificationBell;
