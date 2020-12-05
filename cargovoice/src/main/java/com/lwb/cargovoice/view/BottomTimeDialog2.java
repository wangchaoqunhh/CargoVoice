//package com.lwb.cargovoice.view;
//
//import android.app.Dialog;
//import android.os.Bundle;
//import android.support.design.widget.BottomSheetBehavior;
//import android.support.design.widget.BottomSheetDialog;
//import android.support.design.widget.BottomSheetDialogFragment;
//import android.widget.FrameLayout;
//
//import com.lwb.cargovoice.R;
//
//import static java.security.AccessController.getContext;
//
//public class BottomTimeDialog2 extends BottomSheetDialogFragment {
//    /**
//     * ��������ƫ����
//     */
//    int topOffset = 0;
//    private BottomSheetBehavior<FrameLayout> behavior;
//
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        if (getContext() == null) {
//            return super.onCreateDialog(savedInstanceState);
//        } else {
//            return new BottomSheetDialog(getContext(), R.style.TransparentBottomSheetStyle);
//        }
//    }
//    
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        val mainView = inflater.inflate(R.layout.cargovoice_bottom_carriage_dialog, null)
//        mainView.findViewById<LinearLayout>(R.id.ll_airborne)
//                mainView.findViewById<LinearLayout>(R.id.ll_aquage)
//                mainView.findViewById<LinearLayout>(R.id.ll_airborne)
//                mainView.findViewById<LinearLayout>(R.id.ll_airborne)
//                mainView.findViewById<LinearLayout>(R.id.ll_airborne)
//                mainView.findViewById<LinearLayout>(R.id.ll_airborne)
//        return mainView
//    }
//
//    override fun onStart() {
//        super.onStart()
//        // ��������̲��Զ�����
//        dialog!!.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
//        val dialog = dialog as BottomSheetDialog?
//        val bottomSheet = dialog!!.delegate.findViewById<FrameLayout>(design_bottom_sheet)
//        if (bottomSheet != null) {
//            val layoutParams = bottomSheet.layoutParams as CoordinatorLayout.LayoutParams
//            layoutParams.leftMargin = dp2px(context, 8f)
//            layoutParams.rightMargin = dp2px(context, 8f)
////            behavior = BottomSheetBehavior.from(bottomSheet)
//            // ��ʼΪչ��״̬
////            behavior?.setState(BottomSheetBehavior.STATE_EXPANDED)
//        }
//    }
//
//
//    fun dp2px(context: Context?, dipValue: Float): Int {
//        val scale = context!!.resources.displayMetrics.density
//        return (dipValue * scale + 0.5f).toInt()
//    }// ʹ��Point�Ѿ���ȥ��״̬���߶�
//
//    /**
//     * ��ȡ��Ļ�߶�
//     *
//     * @return height
//     */
//    private fun getHeight(): Int {
//        var height = 1920
//        if (context != null) {
//            val wm = context!!.getSystemService(Context.WINDOW_SERVICE) as WindowManager
//            val point = Point()
//            if (wm != null) {
//                // ʹ��Point�Ѿ���ȥ��״̬���߶�
//                wm.defaultDisplay.getSize(point)
//                height = point.y - topOffset
//            }
//        }
//        return height
//    }
//
//    override fun show(manager: FragmentManager, tag: String?) {
//        if (ClickToolsUtil.isFastDoubleClick) return
//                super.show(manager, tag)
//    }
//}
