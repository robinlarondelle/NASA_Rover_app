//public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {
//
//    private Context mCtx;
//    private ArrayList<MarsRoverPhoto> photoList;
//    private OnItemClickListener listener;
//    private final static String TAG = "PhotoAdapter";
//
//    public void setOnItemClickListener(OnItemClickListener listener) {
//        this.listener = listener;
//    }
//
//
//    // Public vanwege implementatie door PhotoAdapter zelf
//    public class PhotoViewHolder extends RecyclerView.ViewHolder {
//
//        private ImageView imageView;
//        private TextView id;
//
//        private PhotoViewHolder(View itemView) {
//            super(itemView);
//            imageView = itemView.findViewById(R.id.rowImageView);
//            id = itemView.findViewById(R.id.rowTextView);
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (listener != null) {
//                        int position = getAdapterPosition();
//                        if (position != RecyclerView.NO_POSITION) {
//                            listener.onItemClick(position);
//                        }
//                    }
//                }
//            });
//        }
//    }
//}
