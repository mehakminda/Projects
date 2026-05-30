import React from 'react'
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
import {
    Card,
    Grid,
    Typography,
    Tooltip
  } from "@mui/material";
  import axios from "axios";
  import { useEffect, useState } from "react";
import { useNavigate } from 'react-router-dom';

const Search = () => {
    const [post, setPost] = useState(null);
    const navigate = useNavigate();

const handleEdit = (id) => {
  navigate("/edit",{state:{id}});
}

    useEffect(() => {
        const fetchInitialPosts = async () => {
            const response = await axios.get(`http://localhost:8080/getAllJobPosts`);
            setPost(response.data);
        }
         fetchInitialPosts();
      }, []);

      const handleDelete = (id) => {
        async function deletePost() {
          await axios.delete(`http://localhost:8080/jobPost/${id}`);
          console.log("Delete")
      }
      deletePost();
      window.location.reload();
      }

  return (
    <>
      <Grid container spacing={2} sx={{ margin: "2%" }}>
      <Grid item xs={12} sx={12} md={12} lg={12}>
      </Grid>
      {post &&
        post.map((p) => {
          return (
            <Grid key={p.id} item xs={12} md={6} lg={4}>
              <Card sx={{ padding: "3%", overflow: "hidden", width: "84%", backgroundColor:"#ADD8E6" }}>
                <Tooltip title={p.postProfile}>
                <Typography        
                  variant="h4"
                  sx={{ fontSize: "2rem", fontWeight: "600", fontFamily:"sans-serif", overflow:"hidden", textOverflow: "ellipsis", whiteSpace: "nowrap", display: "block", width: "100%" }}
                >
                {p.postProfile}
                </Typography>
                </Tooltip>
                <Typography  sx={{ color: "#585858", marginTop:"2%", fontFamily:"cursive" , overflow: "hidden", textOverflow: "ellipsis", display: "-webkit-box", WebkitLineClamp: 2,  WebkitBoxOrient: "vertical" }} variant="body"
                title= {p.postDesc}
                >
                  
                  Description: {p.postDesc}
                </Typography>
                <br />
                <br />
                <Typography variant="h6" sx={{ fontFamily:"unset", fontSize:"400"}}>
                  Experience: {p.reqExperience} years
                </Typography>
                <Typography sx={{fontFamily:"serif",fontSize:"400"}} gutterBottom  variant="body">Skills : </Typography>
                {p.techStack.map((s, i) => {
                  return (
                    <Typography variant="body" gutterBottom key={i}>
                      {s} .
                      {` `}
                    </Typography>
                  );
                })}
               <DeleteIcon onClick={() => handleDelete(p.postId)} />
                <EditIcon onClick={() => handleEdit(p.postId)} />
              </Card>
            </Grid>
          );
        })}
    </Grid>
    </>
 
  )
}

export default Search