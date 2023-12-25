import React, { useCallback, useEffect, useState } from 'react';
import axios from 'axios';
import { useDropzone } from 'react-dropzone'
import './App.css';

const backendBaseUrl = process.env.REACT_APP_BACKEND_URL_DEV;

const UserProfiles = () => {
  const [userProfiles, setUserProfiles] = useState([]);

  const fetchUserProfiles = () => {
    axios.get(`${backendBaseUrl}/api/v1/user-profile`).then(res => {
      console.log(res);
      setUserProfiles(res.data);
    });
  }

  useEffect(() => {
    console.log(`----------backendBaseUrl: ${backendBaseUrl}`)
    fetchUserProfiles();
  }, [])

  return userProfiles.map((userProfie, index) => {
    return (
      <div key={index}>
        {
          userProfie.userProfileId ?
            <img src={`${backendBaseUrl}/api/v1/user-profile/${userProfie.userProfileId}/image/download`} />
            : null
        }
        <br />
        <br />
        <h1>{userProfie.username}</h1>
        <p>{userProfie.userProfileId}</p>
        {/* <Dropzone userProfileId={userProfie.userProfileId} /> */}
        <Dropzone {...userProfie} />
        <br />
      </div>
    )

  })
};


function Dropzone({ userProfileId }) {
  const onDrop = useCallback(acceptedFiles => {
    // Do something with the files
    const files = acceptedFiles[0];
    console.log(files);

    const formData = new FormData();
    formData.append("file", files);

    axios.post(
      `${backendBaseUrl}/api/v1/user-profile/${userProfileId}/image/upload`,
      formData,
      {
        headers: {
          "Content-Type": "multipart/form-data"
        }
      }
    ).then(() => {
      console.log("file uploaded sucessfully")
    }).catch(err => {
      console.error(err);
    })

  }, [])
  const { getRootProps, getInputProps, isDragActive } = useDropzone({ onDrop })

  return (
    <div {...getRootProps()}>
      <input {...getInputProps()} />
      {
        isDragActive ?
          <p>Drop the files here ...</p> :
          <p>Drag 'n' drop Profile Image, or click to select Profile Image</p>
      }
    </div>
  )
}


function App() {
  return (
    <div className="App">
      <UserProfiles />
    </div>
  );
}

export default App;