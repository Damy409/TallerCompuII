import React, { useEffect, useState } from "react";
import { useOutletContext } from "react-router-dom"; // It's common to import from 'react-router-dom'
import api from "../utils/axios"; // adjust the path accordingly
import Header from "../components/header"; // adjust the path accordingly

interface DecodedToken {
  userId: string;
  lastName: string;
  username: string;
  email: string;
  // add other fields as needed
}

interface UserProfile {
  id: string;
  description: string;
  image: {
    id: number;
    name: string;
  } | null;
}

interface OutletContextType {
  user: DecodedToken | null;
}

export default function ProfilePage() {
  const { user } = useOutletContext<OutletContextType>();

  const [profile, setProfile] = useState<UserProfile | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    if (!user) {
      setError("User not authenticated");
      setLoading(false);
      return;
    }

    api
      .get<UserProfile>(`/api/user-profile/${user.username}`)
      .then((response) => {
        setProfile(response.data);
        setLoading(false);
        console.log("Profile data:", response.data);
      })
      .catch((err) => {
        setError(err.response?.data?.message || err.message);
        setLoading(false);
      });
  }, [user]);

  if (loading) {
    return (
      <div className="flex justify-center items-center h-screen">
        <p className="text-lg text-gray-700">Loading profile...</p>
      </div>
    );
  }

  if (error) {
    return (
      <div className="flex justify-center items-center h-screen">
        <p className="text-red-500 text-lg">{error}</p>
      </div>
    );
  }

  if (!profile) {
    return (
      <div className="flex justify-center items-center h-screen">
        <p className="text-gray-700 text-lg">No profile found.</p>
      </div>
    );
  }

  return (

    <div className="h-screen w-screen">
      <Header />  
      <div className="text-center p-8 max-w-4xl mx-auto bg-white rounded-xl shadow-lg space-y-4 my-8">
        {profile.image ? (
          <img
            src={`${api.defaults.baseURL}/api/images/${profile.image.id}`}
            alt={profile.image.name}
            className="w-32 h-32 rounded-full mx-auto object-cover border-4 border-blue-300 shadow-md"
          />
        ) : (
          <div className="w-32 h-32 rounded-full bg-gray-300 flex items-center justify-center text-gray-600 font-semibold text-sm mx-auto border-4 border-gray-400 shadow-md">
            No Image
          </div>
        )}

        <h2 className="text-3xl font-bold text-gray-800">
          {user?.username} {user?.lastName}
        </h2>
        <p className="text-gray-700 text-lg italic">{profile.description}</p>
        <p className="text-gray-600 text-base">
          <b className="font-semibold">Email:</b> {user?.email}
        </p>
      </div>
      <div className="text-center p-8 max-w-md mx-auto bg-white rounded-xl shadow-lg space-y-4 my-8">
      </div>  


    </div>
  );
}