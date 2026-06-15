/*
 * vertical_cavity_surface_emitting_laser_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:43 by COMSOL 6.3.0.290. */
public class vertical_cavity_surface_emitting_laser_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.param().label("\u51e0\u4f55\u53c2\u6570");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("t_GaAs_DBR", "69.49[nm]", "\u539a\u5ea6\uff0cGaAs DBR \u5c42");
    model.param().set("t_AlGaAs_DBR", "79.63[nm]", "\u539a\u5ea6\uff0cAlGaAs DBR \u5c42");
    model.param().set("t_GaAs_cavity", "136.49[nm]", "\u539a\u5ea6\uff0cGaAs \u8154\u5c42");
    model.param().set("t_QW", "5[nm]", "\u539a\u5ea6\uff0c\u91cf\u5b50\u9631");
    model.param()
         .set("t_AlGaAs_oxide_window_bottom_layer", "if(pos_oxide==1,63.71[nm],if(pos_oxide==2,47.78[nm],if(pos_oxide==3,31.85[nm],if(pos_oxide==4,15.93[nm],0[nm]))))", "\u539a\u5ea6\uff0cAlGaAs\uff0c\u6c27\u5316\u7269\u7a97\u53e3\uff0c\u5e95\u5c42");
    model.param().set("t_oxide", "15.93[nm]", "\u539a\u5ea6\uff0cAlOx");
    model.param()
         .set("t_AlGaAs_oxide_window_second_layer", "63.71[nm]-t_AlGaAs_oxide_window_bottom_layer", "\u539a\u5ea6\uff0cAlGaAs\uff0c\u6c27\u5316\u7269\u7a97\u53e3\uff0c\u7b2c\u4e8c\u5c42");
    model.param().set("d_oxide", "8[um]", "\u76f4\u5f84\uff0c\u6e05\u9664\u6c27\u5316\u7269\u7a97\u53e3");
    model.param().set("t_GaAs_substrate", "1[um]", "\u539a\u5ea6\uff0cGaAs\uff0c\u57fa\u677f");
    model.param().set("d_outer", "12[um]", "\u5916\u5f84");
    model.param().set("N_bottom_DBR", "29", "\u5bf9\u7684\u6570\u91cf\uff0c\u5e95\u90e8 DBR");
    model.param().set("N_top_DBR", "24", "\u5bf9\u7684\u6570\u91cf\uff0c\u9876\u90e8 DBR");
    model.param().set("t_DBR_pair", "t_AlGaAs_DBR+t_GaAs_DBR", "\u539a\u5ea6\uff0cDBR \u5bf9");
    model.param().set("t_bottom_DBR", "N_bottom_DBR*t_DBR_pair+t_AlGaAs_DBR", "\u539a\u5ea6\uff0c\u5e95\u90e8 DBR");
    model.param().set("t_cavity", "2*t_GaAs_cavity+t_QW", "\u539a\u5ea6\uff0c\u8154\u4f53");
    model.param()
         .set("t_oxide_window", "t_AlGaAs_oxide_window_bottom_layer+t_oxide+t_AlGaAs_oxide_window_second_layer+t_GaAs_DBR", "\u539a\u5ea6\uff0c\u6c27\u5316\u7269\u7a97\u53e3");
    model.param().set("t_top_DBR", "N_top_DBR*t_DBR_pair", "\u539a\u5ea6\uff0c\u9876\u90e8 DBR");
    model.param().set("pos_oxide", "3", "\u6c27\u5316\u7269\u4f4d\u7f6e");

    model.geom().create("part1", "Part", 2);
    model.geom("part1").label("DBR \u5bf9");

//    To import content from file, use:
//    model.geom("part1").inputParam().loadFile("FILENAME");
    model.geom("part1").inputParam().set("d1", "69.49[nm]", "\u539a\u5ea6\uff0c\u5e95\u5c42");
    model.geom("part1").inputParam().set("d2", "79.53[nm]", "\u539a\u5ea6\uff0c\u9876\u5c42");
    model.geom("part1").inputParam().set("w", "5[um]", "\u5bbd\u5ea6");
    model.geom("part1").inputParam().set("pos_x", "0[um]", "\u6c34\u5e73\u4f4d\u7f6e");
    model.geom("part1").inputParam().set("pos_y", "0[um]", "\u5782\u76f4\u4f4d\u7f6e");
    model.geom("part1").create("r1", "Rectangle");
    model.geom("part1").feature("r1").label("\u5e95\u5c42");
    model.geom("part1").feature("r1").set("size", new String[]{"w", "d1"});
    model.geom("part1").feature("r1").set("pos", new String[]{"pos_x", "pos_y"});
    model.geom("part1").feature("r1").set("selresult", true);
    model.geom("part1").feature("r1").set("color", "8");
    model.geom("part1").feature().duplicate("r2", "r1");
    model.geom("part1").feature("r2").label("\u9876\u5c42");
    model.geom("part1").feature("r2").set("size", new String[]{"w", "d2"});
    model.geom("part1").feature("r2").set("pos", new String[]{"pos_x", "pos_y+d1"});
    model.geom("part1").feature("r2").set("color", "18");
    model.geom("part1").run("r2");
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d1", "t_AlGaAs_DBR");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d2", "t_GaAs_DBR");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "w", "d_outer/2");
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("AlGaAs \u5c42");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetodom", "pi1_r1.dom", "csel1");
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("GaAs \u5c42");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetodom", "pi1_r2.dom", "csel2");
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").label("\u5e95\u90e8 DBR");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("pi1");
    model.component("comp1").geom("geom1").feature("arr1").set("type", "linear");
    model.component("comp1").geom("geom1").feature("arr1").set("linearsize", "N_bottom_DBR");
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"0", "t_DBR_pair"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").label("\u5e95\u90e8 DBR \u4e2d\u7684\u9876\u5c42");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"d_outer/2", "t_AlGaAs_DBR"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"0", "N_bottom_DBR*t_DBR_pair"});
    model.component("comp1").geom("geom1").feature("r1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature().duplicate("r2", "r1");
    model.component("comp1").geom("geom1").feature("r2").label("Lambda \u8154\u4e2d\u7684\u5e95\u90e8 GaAs \u5c42");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"d_outer/2", "t_GaAs_cavity"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"0", "t_bottom_DBR"});
    model.component("comp1").geom("geom1").feature("r2").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature().duplicate("r3", "r2");
    model.component("comp1").geom("geom1").feature("r3").label("Lambda \u8154\u4e2d\u7684 QW \u589e\u76ca\u57df");
    model.component("comp1").geom("geom1").feature("r3").setIndex("size", "d_oxide/2", 0);
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"d_oxide/2", "t_QW"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"0", "t_bottom_DBR+t_GaAs_cavity"});
    model.component("comp1").geom("geom1").selection().create("csel3", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel3").label("QW \u589e\u76ca\u57df");
    model.component("comp1").geom("geom1").feature("r3").set("contributeto", "csel3");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature().duplicate("r4", "r3");
    model.component("comp1").geom("geom1").feature("r4").label("Lambda \u8154\u4e2d\u7684 QW \u635f\u8017\u57df");
    model.component("comp1").geom("geom1").feature("r4").set("size", new String[]{"(d_outer-d_oxide)/2", "t_QW"});
    model.component("comp1").geom("geom1").feature("r4").setIndex("pos", "d_oxide/2", 0);
    model.component("comp1").geom("geom1").selection().create("csel4", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel4").label("QW \u635f\u8017\u57df");
    model.component("comp1").geom("geom1").feature("r4").set("contributeto", "csel4");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature().duplicate("r5", "r2");
    model.component("comp1").geom("geom1").feature("r5").label("Lambda \u8154\u4e2d\u7684\u9876\u90e8 GaAs \u5c42");
    model.component("comp1").geom("geom1").feature("r5")
         .set("pos", new String[]{"0", "t_bottom_DBR+t_GaAs_cavity+t_QW"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("if1", "If");
    model.component("comp1").geom("geom1").feature().createAfter("endif1", "EndIf", "if1");
    model.component("comp1").geom("geom1").feature("if1")
         .label("If \u6c27\u5316\u7269\u7a97\u53e3\u4e2d\u7684\u5e95\u90e8 AlGaAs \u5c42\u662f\u6709\u9650\u7684");
    model.component("comp1").geom("geom1").feature("if1").set("condition", "t_AlGaAs_oxide_window_bottom_layer>0");
    model.component("comp1").geom("geom1").feature().duplicate("r6", "r1");
    model.component("comp1").geom("geom1").feature("r6")
         .label("\u6c27\u5316\u7269\u7a97\u53e3\u4e2d\u7684\u5e95\u90e8 AlGaAs \u5c42");
    model.component("comp1").geom("geom1").feature("r6")
         .set("size", new String[]{"d_outer/2", "t_AlGaAs_oxide_window_bottom_layer"});
    model.component("comp1").geom("geom1").feature("r6").set("pos", new String[]{"0", "t_bottom_DBR+t_cavity"});
    model.component("comp1").geom("geom1").feature("endif1")
         .label("End If \u6c27\u5316\u7269\u7a97\u53e3\u4e2d\u7684\u5e95\u90e8 AlGaAs \u5c42\u662f\u6709\u9650\u7684");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature().duplicate("r7", "r6");
    model.component("comp1").geom("geom1").feature("r7")
         .label("\u6c27\u5316\u7269\u7a97\u53e3\u4e2d\u7684 AlAs \u57df");
    model.component("comp1").geom("geom1").feature("r7").setIndex("size", "d_oxide/2", 0);
    model.component("comp1").geom("geom1").feature("r7").set("size", new String[]{"d_oxide/2", "t_oxide"});
    model.component("comp1").geom("geom1").feature("r7")
         .set("pos", new String[]{"0", "t_bottom_DBR+t_cavity+t_AlGaAs_oxide_window_bottom_layer"});
    model.component("comp1").geom("geom1").selection().create("csel5", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel5").label("AlAs \u57df");
    model.component("comp1").geom("geom1").feature("r7").set("contributeto", "csel5");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature().duplicate("r8", "r7");
    model.component("comp1").geom("geom1").feature("r8")
         .label("\u6c27\u5316\u7269\u7a97\u53e3\u4e2d\u7684 AlOx \u57df");
    model.component("comp1").geom("geom1").feature("r8").set("size", new String[]{"(d_outer-d_oxide)/2", "t_oxide"});
    model.component("comp1").geom("geom1").feature("r8").setIndex("pos", "d_oxide/2", 0);
    model.component("comp1").geom("geom1").selection().create("csel6", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel6").label("AlOx \u57df");
    model.component("comp1").geom("geom1").feature("r8").set("contributeto", "csel6");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature().duplicate("if2", "if1");
    model.component("comp1").geom("geom1").feature().duplicate("r9", "r6");
    model.component("comp1").geom("geom1").feature().duplicate("endif2", "endif1");
    model.component("comp1").geom("geom1").feature("if2")
         .label("If \u6c27\u5316\u7269\u7a97\u53e3\u4e2d\u7684\u7b2c\u4e8c\u4e2a AlGaAs \u5c42\u662f\u6709\u9650\u7684");
    model.component("comp1").geom("geom1").feature("if2").set("condition", "t_AlGaAs_oxide_window_second_layer>0");
    model.component("comp1").geom("geom1").feature("r9")
         .label("\u6c27\u5316\u7269\u7a97\u53e3\u4e2d\u7684\u7b2c\u4e8c\u4e2a AlGaAs \u5c42");
    model.component("comp1").geom("geom1").feature("r9")
         .set("size", new String[]{"d_outer/2", "t_AlGaAs_oxide_window_second_layer"});
    model.component("comp1").geom("geom1").feature("r9")
         .set("pos", new String[]{"0", "t_bottom_DBR+t_cavity+t_AlGaAs_oxide_window_bottom_layer+t_oxide"});
    model.component("comp1").geom("geom1").feature("endif2")
         .label("End If \u6c27\u5316\u7269\u7a97\u53e3\u4e2d\u7684\u7b2c\u4e8c\u4e2a AlGaAs \u5c42\u662f\u6709\u9650\u7684");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature().duplicate("r10", "r2");
    model.component("comp1").geom("geom1").feature("r10")
         .label("\u6c27\u5316\u7269\u7a97\u53e3\u4e2d\u7684\u9876\u90e8 GaAs \u5c42");
    model.component("comp1").geom("geom1").feature("r10").set("size", new String[]{"d_outer/2", "t_GaAs_DBR"});
    model.component("comp1").geom("geom1").feature("r10")
         .set("pos", new String[]{"0", "t_bottom_DBR+t_cavity+t_AlGaAs_oxide_window_bottom_layer+t_oxide+t_AlGaAs_oxide_window_second_layer"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature().duplicate("pi2", "pi1");
    model.component("comp1").geom("geom1").feature("pi1").set("selcontributetoobj", new String[]{});
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepobj", new String[]{});
    model.component("comp1").geom("geom1").feature("pi1").set("selcontributetopnt", new String[]{});
    model.component("comp1").geom("geom1").feature("pi1").set("selkeeppnt", new String[]{});
    model.component("comp1").geom("geom1").feature("pi1").set("selshowpnt", new String[]{});
    model.component("comp1").geom("geom1").feature("pi1").set("selcontributetobnd", new String[]{});
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepbnd", new String[]{});
    model.component("comp1").geom("geom1").feature("pi1").set("selshowbnd", new String[]{});
    model.component("comp1").geom("geom1").feature("pi1").set("selcontributetodom", new String[]{"csel1", "csel2"});
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepdom", new String[]{"off", "off"});
    model.component("comp1").geom("geom1").feature("pi1").set("selshowdom", new String[]{"on", "on"});
    model.component("comp1").geom("geom1").feature("pi2")
         .setEntry("inputexpr", "pos_y", "t_bottom_DBR+t_cavity+t_oxide_window");
    model.component("comp1").geom("geom1").run("pi2");
    model.component("comp1").geom("geom1").create("arr2", "Array");
    model.component("comp1").geom("geom1").feature("arr2").label("\u9876\u90e8 DBR");
    model.component("comp1").geom("geom1").feature("arr2").selection("input").set("pi2");
    model.component("comp1").geom("geom1").feature("arr2").set("type", "linear");
    model.component("comp1").geom("geom1").feature("arr2").set("linearsize", "N_top_DBR");
    model.component("comp1").geom("geom1").feature("arr2").set("displ", new String[]{"0", "t_DBR_pair"});
    model.component("comp1").geom("geom1").runPre("fin");

    model.title(null);

    model.description("");

    model.label("vertical_cavity_surface_emitting_laser_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
