"use client";

import { useEffect, useState } from 'react';
import axios, { AxiosResponse } from 'axios';

export default function Home() {
  const [message, setMessage] = useState<string>('');

  useEffect(() => {
    axios.get('/api/hello.json')
      .then((response: AxiosResponse<string>) => {
        setMessage(response.data);
      })
      .catch((error: any) => {
        console.error('There was an error fetching the data!', error);
      });
  }, []);

  return (
    <div className="font-sans grid grid-rows-[20px_1fr_20px] items-center justify-items-center min-h-screen p-8 pb-20 gap-16 sm:p-20">
      <main className="flex flex-col gap-[32px] row-start-2 items-center sm:items-start">
        <div className="bg-gray-100 p-4 rounded-md">
          <p className="text-gray-800">{message}</p>
        </div>
      </main>
    </div>
  );
}
