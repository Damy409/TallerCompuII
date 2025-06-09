import React, { useState } from 'react';
import axios from 'axios';

const CreateProfilePage = () => {
  const [form, setForm] = useState({
    name: '',
    lastname: '',
    email: '',
    password: '',
    profileDescription: '',
    imageName: '',
    imageType: '',
    imageFile: null,
  });

  const handleChange = (e) => {
    const { name, value, files } = e.target;
    if (name === 'imageFile') {
      setForm({ ...form, imageFile: files[0] });
    } else {
      setForm({ ...form, [name]: value });
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!form.imageFile) {
      alert('Please upload an image.');
      return;
    }

    const reader = new FileReader();
    reader.onload = async () => {
      const imageData = new Uint8Array(reader.result);

      const payload = {
        name: form.name,
        lastname: form.lastname,
        email: form.email,
        password: form.password,
        profileDescription: form.profileDescription,
        imageName: form.imageName,
        imageType: form.imageType,
        imageData: Array.from(imageData),
      };

      try {
        const response = await axios.post('/api/users/with-profile', payload);
        alert('User created successfully!');
      } catch (error) {
        console.error(error);
        alert('Failed to create user.');
      }
    };

    reader.readAsArrayBuffer(form.imageFile);
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Create User with Profile</h2>
      <input name="name" placeholder="First Name" value={form.name} onChange={handleChange} required />
      <input name="lastname" placeholder="Last Name" value={form.lastname} onChange={handleChange} required />
      <input name="email" type="email" placeholder="Email" value={form.email} onChange={handleChange} required />
      <input name="password" type="password" placeholder="Password" value={form.password} onChange={handleChange} required />
      <textarea name="profileDescription" placeholder="Profile Description" value={form.profileDescription} onChange={handleChange} required />

      <input name="imageName" placeholder="Image Name" value={form.imageName} onChange={handleChange} required />
      <input name="imageType" placeholder="Image Type (e.g. image/png)" value={form.imageType} onChange={handleChange} required />
      <input name="imageFile" type="file" accept="image/*" onChange={handleChange} required />

      <button type="submit">Create User</button>
    </form>
  );
};

export default CreateProfilePage;
