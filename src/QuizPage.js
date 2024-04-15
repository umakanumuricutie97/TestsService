// src/QuizPage.js
import React, { useState } from 'react';
import axios from 'axios';

function QuizPage() {
  const [questionNumber, setQuestionNumber] = useState('');
  const [questionText, setQuestionText] = useState('');
  const [answer, setAnswer] = useState('');
  const [result, setResult] = useState('');

  const fetchQuestion = () => {
    axios.get(`http://localhost:8081/quiz/${questionNumber}`)
      .then(response => {
        setQuestionText(response.data);
      })
      .catch(error => {
        console.error('Error fetching question:', error);
      });
  };

  const submitAnswer = () => {
    axios.post(`http://localhost:8081/quiz/${questionNumber}/answer`, answer)
      .then(response => {
        setResult(response.data);
      })
      .catch(error => {
        console.error('Error submitting answer:', error);
      });
  };

  return (
    <div>
      <h1>Quiz Page</h1>
      <input 
        type="text" 
        placeholder="Enter question number" 
        value={questionNumber}
        onChange={e => setQuestionNumber(e.target.value)} 
      />
      <button onClick={fetchQuestion}>Fetch Question</button>
      <br />
      {questionText && (
        <>
          <p>Question: {questionText}</p>
          <input 
            type="text" 
            placeholder="Enter your answer" 
            value={answer}
            onChange={e => setAnswer(e.target.value)} 
          />
          <button onClick={submitAnswer}>Submit Answer</button>
          <br />
          {result && <p>{result}</p>}
        </>
      )}
    </div>
  );
}

export default QuizPage;
