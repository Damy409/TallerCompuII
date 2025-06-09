import { useEffect, useState } from 'react';
import instance from '../utils/axios';

interface Notification {
  id: string;
  name: string;
  description: string;
  date: string;
}

function NotificationList({ userId }: { userId: string }) {
  const [notifications, setNotifications] = useState<Notification[]>([]);

  useEffect(() => {
    const fetchNotifications = async () => {
      try {
        const res = await instance.get(`/api/notificaciones/${userId}`);
        setNotifications(res.data);
      } catch (error) {
        console.error('Error al cargar notificaciones', error);
      }
    };

    fetchNotifications();
  }, [userId]);

  return (
    <div className="absolute right-0 mt-2 w-80 bg-white border border-blue-300 rounded shadow-lg z-50 p-4">
      <h3 className="text-blue-700 font-bold mb-2">Notificaciones</h3>
      {notifications.length === 0 ? (
        <p className="text-sm text-gray-500">No hay notificaciones.</p>
      ) : (
        <ul className="space-y-2">
          {notifications.map((n) => (
            <li key={n.id} className="text-sm border-b pb-2">
              <strong>{n.name}</strong>
              <p>{n.description}</p>
              <span className="text-xs text-gray-400">{n.date}</span>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}

export default NotificationList;
