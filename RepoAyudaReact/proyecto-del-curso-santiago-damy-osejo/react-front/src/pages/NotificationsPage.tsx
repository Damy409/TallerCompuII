import { useEffect, useState } from 'react';
import instance from '../utils/axios';

interface Notification {
  id: string;
  name: string;
  description: string;
  date: string;
}

function NotificationsPage() {
  const [notifications, setNotifications] = useState<Notification[]>([]);
  const userId = localStorage.getItem('userId');

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
    <div className="min-h-screen flex flex-col items-center justify-start bg-blue-100 p-6">
      <h2 className="text-2xl font-bold text-blue-700 mb-6">Tus Notificaciones</h2>
      {notifications.length === 0 ? (
        <p className="text-gray-500">No tienes notificaciones aún.</p>
      ) : (
        <ul className="space-y-4 w-full max-w-2xl">
          {notifications.map((n) => (
            <li key={n.id} className="bg-white p-4 rounded shadow border border-blue-200">
              <strong>{n.name}</strong>
              <p>{n.description}</p>
              <span className="text-xs text-gray-500">{n.date}</span>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}

export default NotificationsPage;

