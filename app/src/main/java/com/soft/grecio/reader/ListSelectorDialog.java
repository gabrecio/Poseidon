
package com.soft.grecio.reader;

/**
 * Created by grecio on 5/4/2015.
 */
        import java.util.ArrayList;

        import java.util.HashMap;
        import android.app.AlertDialog;
        import android.app.AlertDialog.Builder;
        import android.content.Context;
        import android.content.DialogInterface;

public class ListSelectorDialog {
    Context context;
    Builder adb;
    String title;

    // our interface so we can return the selected key/item pair.
    public interface listSelectorInterface {
        void selectedItem(String key, String item);
        void selectorCanceled();
    }

    ListSelectorDialog(Context c) {
        this.context = c;
    }
    ListSelectorDialog(Context c, String newTitle) {
        this.context = c;
        this.title = newTitle;
    }

    ListSelectorDialog setTitle(String newTitle) {
        this.title = newTitle;
        return this;
    }

    ListSelectorDialog show(ArrayList il, final listSelectorInterface di) {
        String l[] = new String[il.size()];

        show(l, l, di);
        return this;
    }

    ListSelectorDialog show(ArrayList il, ArrayList ik,
                            final listSelectorInterface di) {
        // convert the ArrayList's to standard Java arrays.
        String l[] = new String[il.size()];
        String k[] = new String[ik.size()];



        show(l, k, di);
        return this;
    }

    ListSelectorDialog show(HashMap hashmap, final listSelectorInterface di) {
        // convert the hashmap to lists
        String[] il = new String[hashmap.size()];
        String[] ik = new String[hashmap.size()];
        // HashMap iteration
        int i = 0;
        for (Object key: hashmap.keySet()) {
            il[i] = key.toString();
            ik[i] = hashmap.get(key).toString();
            i++;
        }
        // now show the selection dialog
        show(il, ik, di);
        return this;
    }

    ListSelectorDialog show(final String[] itemList, final listSelectorInterface di) {
        // if only 1 list supplied, the list serves as both keys and values.
        show(itemList, itemList, di);
        return this;
    }

    ListSelectorDialog show(final String[] itemList, final String[] keyList,
                            final listSelectorInterface di) {
        // set up the dialog
        adb = new AlertDialog.Builder(context);
        adb.setCancelable(false);
        adb.setItems(itemList, new DialogInterface.OnClickListener() {
            // when an item is clicked, notify our interface
            public void onClick(DialogInterface d, int n) {
                d.dismiss();
                di.selectedItem(keyList[n], itemList[n]);
            }
        });
        adb.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            // when user clicks cancel, notify our interface
            public void onClick(DialogInterface d, int n) {
                d.dismiss();
                di.selectorCanceled();
            }
        });
        adb.setTitle(title);
        // show the dialog
        adb.show();
        return this;
    }
}