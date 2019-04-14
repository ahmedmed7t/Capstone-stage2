package omdvet.com;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DelegatesAdapter extends RecyclerView.Adapter<DelegatesAdapter.ViewHolder> {

    private ArrayList<String> delegateNameList;
    private ArrayList<String> delegatePhoneList;
    private ArrayList<String> delegateMailList;
    private ArrayList<String> delegateIDList;
    private ArrayList<String> delegateAddressList;
    private ArrayList<String> delegateSalesList;
    private ArrayList<String> delegateSalesValueList;
    Context context;
    public DelegatesAdapter( Context context,
                           ArrayList<String> delegateNameList,
                           ArrayList<String> delegatePhoneList,
                           ArrayList<String> delegateMailList,
                           ArrayList<String> delegateIDList,
                           ArrayList<String> delegateAddressList,
                           ArrayList<String> delegateSalesList,
                           ArrayList<String> delegateSalesValueList
                            )
    {
        this.context=context;
        this.delegateNameList=delegateNameList;
        this.delegatePhoneList=delegatePhoneList;
        this.delegateMailList=delegateMailList;
        this.delegateIDList=delegateIDList;
        this.delegateAddressList=delegateAddressList;
        this.delegateSalesList=delegateSalesList;
        this.delegateSalesValueList=delegateSalesValueList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView delegateName;
        public TextView delegatePhone;
        public TextView delegateMail;
        public TextView delegateID;
        public TextView delegateAddress;
        public TextView delegateSales;
        public TextView delegateSalesValue;
        public View view;

        public ViewHolder(View v) {
            super(v);
            delegateName = (TextView) v.findViewById(R.id.delegateName);
            delegatePhone = (TextView) v.findViewById(R.id.delegatePhone);
            delegateMail = (TextView) v.findViewById(R.id.delegateMail);
            delegateID = (TextView) v.findViewById(R.id.delegateID);
            delegateAddress = (TextView) v.findViewById(R.id.delegateAddress);
            delegateSales = (TextView) v.findViewById(R.id.delegateSales);
            delegateSalesValue = v.findViewById(R.id.delegateSalesValue);

            view = v.findViewById(R.id.delegate_view);

        }
    }
    @Override
    public DelegatesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.delegates_item,parent,false);
        return new DelegatesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DelegatesAdapter.ViewHolder holder, int position) {
        holder.delegateName.setText(delegateNameList.get(position));
        holder.delegatePhone.setText(delegatePhoneList.get(position));
        holder.delegateMail.setText(delegateMailList.get(position));
        holder.delegateID.setText(delegateIDList.get(position));
        holder.delegateAddress.setText(delegateAddressList.get(position));
        holder.delegateSales.setText(delegateSalesList.get(position));
        holder.delegateSalesValue.setText(delegateSalesValueList.get(position));

        if(position == delegateIDList.size()-1){
            holder.view.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return delegateNameList.size();
    }

}
