
//
//        // RecyclerView met lijst van foto's die opgehaald worden uit lokale database
//        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//        photoList.addAll(database.getAllPhotos());
//
//        // Starten van de asynchrone taak
//        try {
//            asyncTask.execute(urls);
//            Log.i(TAG, "Asynchrone taak gestart.");
//        } catch (Exception e) {
//            e.printStackTrace();
//            Log.e(TAG, "ERROR: Kan ApiAsyncTask.execute() niet uitvoeren vanuit MainActivity.");
//        }
//        recyclerView.setAdapter(photoAdapter);
//        photoAdapter.setOnItemClickListener(MainActivity.this);
//        photoAdapter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void onItemClick(int position) {
//        Log.i(TAG, "onItemClick() aangeroepen.");
//        Intent detailIntent = new Intent(this, DetailActivity.class);
//        MarsRoverPhoto clickedPhoto = photoList.get(position);
//        detailIntent.putExtra(CLICKED_PHOTO, clickedPhoto);
//        startActivity(detailIntent);
//        Log.i(TAG, "DetailActivity gestart.");
//    }
//
//    public void onTaskCompleted(MarsRoverPhoto photo) {
//        Log.i(TAG, "onTaskCompleted() aangeroepen vanuit asynchrone taak.");
//        database.addPhoto(photo);
//        Log.i(TAG, "Foto met ID " + photo.getId() + " toegevoegd aan database.");
//    }
//}
