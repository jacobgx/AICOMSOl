/*
 * tweeter_shape_optimization_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:45 by COMSOL 6.3.0.290. */
public class tweeter_shape_optimization_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Optimization_Module\\Shape_Optimization");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("r_model", "sin(angle_eval/2)*r_eval+2*th_pml", "\u58f0\u5b66\u57df\u534a\u5f84");
    model.param()
         .set("h_model", "r_eval-(-h_former+h_voice_coil/2-h_top_plate/2-h_back-h_waveguide)+2*th_pml", "\u58f0\u5b66\u57df\u9ad8\u5ea6");
    model.param().set("th_pml", "5[mm]", "PML \u5c42\u539a\u5ea6");
    model.param().set("d_voice_coil", "25[mm]", "\u97f3\u5708\u534a\u5f84");
    model.param().set("h_voice_coil", "3[mm]", "\u97f3\u5708\u9ad8\u5ea6");
    model.param().set("th_voice_coil", "0.5[mm]", "\u97f3\u5708\u539a\u5ea6");
    model.param().set("h_former", "15[mm]", "\u97f3\u5708\u9aa8\u67b6\u9ad8\u5ea6");
    model.param().set("h_top_plate", "6[mm]", "\u6781\u7247\u9ad8\u5ea6");
    model.param().set("air_gap", "0.5[mm]", "\u6c14\u9699");
    model.param().set("h_back", "8[mm]", "\u540e\u8154\u4f53\u9ad8\u5ea6");
    model.param().set("d_magnet", "30[mm]", "\u78c1\u4f53\u5185\u5f84");
    model.param().set("r1_susp", "0.5[mm]", "\u60ac\u67b6\u7684\u6700\u5c0f\u534a\u5f84");
    model.param().set("r2_susp", "1[mm]", "\u60ac\u67b6\u7684\u6700\u5927\u534a\u5f84");
    model.param().set("d_waveguide", "100[mm]", "\u6ce2\u5bfc\u76f4\u5f84");
    model.param().set("d1", "12.5[mm]", "\u7b2c\u4e00\u4e2a\u9ad8\u97f3\u7f69\u5c3a\u5bf8");
    model.param().set("d2", "12[mm]", "\u7b2c\u4e8c\u4e2a\u9ad8\u97f3\u7f69\u5c3a\u5bf8");
    model.param().set("d3", "10.5[mm]", "\u7b2c\u4e09\u4e2a\u9ad8\u97f3\u7f69\u5c3a\u5bf8");
    model.param().set("d4", "8[mm]", "\u7b2c\u56db\u4e2a\u9ad8\u97f3\u7f69\u5c3a\u5bf8");
    model.param().set("d5", "4.5[mm]", "\u7b2c\u4e94\u4e2a\u9ad8\u97f3\u7f69\u5c3a\u5bf8");
    model.param().set("alpha1", "0[deg]", "\u9ad8\u97f3\u7f69\u8f74\u89d2\u5ea6");
    model.param().set("alpha2", "60[deg]", "\u9ad8\u97f3\u7f69\u6700\u7ec8\u89d2\u5ea6");
    model.param().set("w1", "8[mm]", "\u7b2c\u4e00\u4e2a\u6ce2\u5bfc\u5c3a\u5bf8");
    model.param().set("w2", "11[mm]", "\u7b2c\u4e8c\u4e2a\u6ce2\u5bfc\u5c3a\u5bf8");
    model.param().set("w3", "14.25[mm]", "\u7b2c\u4e09\u4e2a\u6ce2\u5bfc\u5c3a\u5bf8");
    model.param().set("w4", "17[mm]", "\u7b2c\u56db\u4e2a\u6ce2\u5bfc\u5c3a\u5bf8");
    model.param().set("beta1", "90[deg]", "\u521d\u59cb\u6ce2\u5bfc\u89d2\u5ea6");
    model.param().set("beta2", "30[deg]", "\u6700\u7ec8\u6ce2\u5bfc\u89d2\u5ea6");
    model.param().set("h_waveguide", "20[mm]", "\u6ce2\u5bfc\u9ad8\u5ea6");
    model.param().set("d_porous", "18[mm]", "\u591a\u5b54\u5438\u58f0\u4f53\u76f4\u5f84");
    model.param().set("h_porous", "7[mm]", "\u591a\u5b54\u5438\u58f0\u4f53\u9ad8\u5ea6");
    model.param().set("r_eval", "600[mm]", "\u8fd1\u573a\u7684\u8ba1\u7b97\u534a\u5f84");
    model.param().set("angle_eval", "20[deg]", "\u626c\u58f0\u5668\u524d\u65b9\u7684\u89d2\u5ea6\u5bbd\u5ea6");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("angle", 90);
    model.component("comp1").geom("geom1").feature("c1").set("r", "r_model+th_pml");
    model.component("comp1").geom("geom1").feature("c1").setIndex("layer", "th_pml", 0);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"r_model+th_pml", "1"});
    model.component("comp1").geom("geom1").feature("r1")
         .setIndex("size", "r_model+th_pml+h_former-h_voice_coil/2+h_top_plate/2+h_back+h_waveguide", 1);
    model.component("comp1").geom("geom1").feature("r1")
         .set("pos", new String[]{"0", "-h_former+h_voice_coil/2-h_top_plate/2-h_back-h_waveguide"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"th_voice_coil+2*air_gap", "1"});
    model.component("comp1").geom("geom1").feature("r2").setIndex("size", "h_voice_coil", 1);
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"d_voice_coil/2-air_gap", "0"});
    model.component("comp1").geom("geom1").feature("r2").setIndex("pos", "-h_former-h_waveguide", 1);
    model.component("comp1").geom("geom1").feature("r2").setIndex("layer", "air_gap", 0);
    model.component("comp1").geom("geom1").feature("r2").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("r2").set("layerright", true);
    model.component("comp1").geom("geom1").feature("r2").set("layerbottom", false);
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("pol1")
         .setIndex("table", "-h_former+h_voice_coil/2+h_top_plate/2-h_waveguide", 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "d_voice_coil/2-air_gap", 1, 0);
    model.component("comp1").geom("geom1").feature("pol1")
         .setIndex("table", "-h_former+h_voice_coil/2+h_top_plate/2-h_waveguide", 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "d_voice_coil/2-air_gap", 2, 0);
    model.component("comp1").geom("geom1").feature("pol1")
         .setIndex("table", "-h_former+h_voice_coil/2-h_top_plate/2-h_back-h_waveguide", 2, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "d_magnet/2", 3, 0);
    model.component("comp1").geom("geom1").feature("pol1")
         .setIndex("table", "-h_former+h_voice_coil/2-h_top_plate/2-h_back-h_waveguide", 3, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "d_magnet/2", 4, 0);
    model.component("comp1").geom("geom1").feature("pol1")
         .setIndex("table", "-h_former+h_voice_coil/2-h_top_plate/2-h_waveguide", 4, 1);
    model.component("comp1").geom("geom1").feature("pol1")
         .setIndex("table", "d_voice_coil/2+th_voice_coil+air_gap", 5, 0);
    model.component("comp1").geom("geom1").feature("pol1")
         .setIndex("table", "-h_former+h_voice_coil/2-h_top_plate/2-h_waveguide", 5, 1);
    model.component("comp1").geom("geom1").feature("pol1")
         .setIndex("table", "d_voice_coil/2+th_voice_coil+air_gap", 6, 0);
    model.component("comp1").geom("geom1").feature("pol1")
         .setIndex("table", "-h_former+h_voice_coil/2+h_top_plate/2-h_waveguide", 6, 1);
    model.component("comp1").geom("geom1").feature("pol1")
         .setIndex("table", "d_voice_coil/2+4*r1_susp+2*r2_susp", 7, 0);
    model.component("comp1").geom("geom1").feature("pol1")
         .setIndex("table", "-h_former+h_voice_coil/2+h_top_plate/2-h_waveguide", 7, 1);
    model.component("comp1").geom("geom1").feature("pol1")
         .setIndex("table", "d_voice_coil/2+4*r1_susp+2*r2_susp", 8, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-h_waveguide", 8, 1);
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("ic1", "InterpolationCurve");
    model.component("comp1").geom("geom1").feature("ic1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("ic1").setIndex("table", "d1-h_waveguide", 0, 1);
    model.component("comp1").geom("geom1").feature("ic1").setIndex("table", "(d_voice_coil/2)*1/5", 1, 0);
    model.component("comp1").geom("geom1").feature("ic1").setIndex("table", "d2-h_waveguide", 1, 1);
    model.component("comp1").geom("geom1").feature("ic1").setIndex("table", "(d_voice_coil/2)*2/5", 2, 0);
    model.component("comp1").geom("geom1").feature("ic1").setIndex("table", "d3-h_waveguide", 2, 1);
    model.component("comp1").geom("geom1").feature("ic1").setIndex("table", "(d_voice_coil/2)*3/5", 3, 0);
    model.component("comp1").geom("geom1").feature("ic1").setIndex("table", "d4-h_waveguide", 3, 1);
    model.component("comp1").geom("geom1").feature("ic1").setIndex("table", "(d_voice_coil/2)*4/5", 4, 0);
    model.component("comp1").geom("geom1").feature("ic1").setIndex("table", "d5-h_waveguide", 4, 1);
    model.component("comp1").geom("geom1").feature("ic1").setIndex("table", "(d_voice_coil/2)*5/5", 5, 0);
    model.component("comp1").geom("geom1").feature("ic1").setIndex("table", "-h_waveguide", 5, 1);
    model.component("comp1").geom("geom1").feature("ic1").set("startcond", "tangent");
    model.component("comp1").geom("geom1").feature("ic1").set("starttang", new String[]{"cos(alpha1)", "0"});
    model.component("comp1").geom("geom1").feature("ic1").setIndex("starttang", "-sin(alpha1)", 1);
    model.component("comp1").geom("geom1").feature("ic1").set("endcond", "tangent");
    model.component("comp1").geom("geom1").feature("ic1").set("endtang", new String[]{"cos(alpha2)", "0"});
    model.component("comp1").geom("geom1").feature("ic1").setIndex("endtang", "-sin(alpha2)", 1);
    model.component("comp1").geom("geom1").run("ic1");
    model.component("comp1").geom("geom1").create("ic2", "InterpolationCurve");
    model.component("comp1").geom("geom1").feature("ic2")
         .setIndex("table", "d_voice_coil/2+4*r1_susp+2*r2_susp", 0, 0);
    model.component("comp1").geom("geom1").feature("ic2").setIndex("table", "-h_waveguide", 0, 1);
    model.component("comp1").geom("geom1").feature("ic2")
         .setIndex("table", "d_voice_coil/2+4*r1_susp+2*r2_susp+(d_waveguide/2-(d_voice_coil/2+4*r1_susp+2*r2_susp))*1/5", 1, 0);
    model.component("comp1").geom("geom1").feature("ic2").setIndex("table", "w1-h_waveguide", 1, 1);
    model.component("comp1").geom("geom1").feature("ic2")
         .setIndex("table", "d_voice_coil/2+4*r1_susp+2*r2_susp+(d_waveguide/2-(d_voice_coil/2+4*r1_susp+2*r2_susp))*2/5", 2, 0);
    model.component("comp1").geom("geom1").feature("ic2").setIndex("table", "w2-h_waveguide", 2, 1);
    model.component("comp1").geom("geom1").feature("ic2")
         .setIndex("table", "d_voice_coil/2+4*r1_susp+2*r2_susp+(d_waveguide/2-(d_voice_coil/2+4*r1_susp+2*r2_susp))*3/5", 3, 0);
    model.component("comp1").geom("geom1").feature("ic2").setIndex("table", "w3-h_waveguide", 3, 1);
    model.component("comp1").geom("geom1").feature("ic2")
         .setIndex("table", "d_voice_coil/2+4*r1_susp+2*r2_susp+(d_waveguide/2-(d_voice_coil/2+4*r1_susp+2*r2_susp))*4/5", 4, 0);
    model.component("comp1").geom("geom1").feature("ic2").setIndex("table", "w4-h_waveguide", 4, 1);
    model.component("comp1").geom("geom1").feature("ic2")
         .setIndex("table", "d_voice_coil/2+4*r1_susp+2*r2_susp+(d_waveguide/2-(d_voice_coil/2+4*r1_susp+2*r2_susp))*5/5", 5, 0);
    model.component("comp1").geom("geom1").feature("ic2").setIndex("table", 0, 5, 1);
    model.component("comp1").geom("geom1").feature("ic2").set("startcond", "tangent");
    model.component("comp1").geom("geom1").feature("ic2").set("starttang", new String[]{"cos(beta1)", "sin(beta1)"});
    model.component("comp1").geom("geom1").feature("ic2").set("endcond", "tangent");
    model.component("comp1").geom("geom1").feature("ic2").set("endtang", new String[]{"cos(beta2)", "sin(beta2)"});
    model.component("comp1").geom("geom1").run("ic2");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").label("\u7ebf\u5708\u67b6");
    model.component("comp1").geom("geom1").feature("ls1").selection("vertex1").set("ic1", 2);
    model.component("comp1").geom("geom1").feature("ls1").selection("vertex2").set("r2", 3);
    model.component("comp1").geom("geom1").feature("ls1").set("selresult", true);
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").create("ls2", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls2").selection("vertex1").set("ic2", 2);
    model.component("comp1").geom("geom1").feature("ls2").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls2").set("coord2", new String[]{"r_model+th_pml", "0"});
    model.component("comp1").geom("geom1").run("ls2");
    model.component("comp1").geom("geom1").create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("ca1").set("center", new String[]{"d_voice_coil/2+r1_susp", "0"});
    model.component("comp1").geom("geom1").feature("ca1")
         .setIndex("center", "(-h_former+h_voice_coil/2+h_top_plate/2)*2/3-h_waveguide", 1);
    model.component("comp1").geom("geom1").feature("ca1").set("r", "r1_susp");
    model.component("comp1").geom("geom1").feature("ca1").set("angle2", 180);
    model.component("comp1").geom("geom1").run("ca1");
    model.component("comp1").geom("geom1").create("ca2", "CircularArc");
    model.component("comp1").geom("geom1").feature("ca2")
         .set("center", new String[]{"d_voice_coil/2+2*r1_susp+r2_susp", "0"});
    model.component("comp1").geom("geom1").feature("ca2")
         .setIndex("center", "(-h_former+h_voice_coil/2+h_top_plate/2)*2/3-h_waveguide", 1);
    model.component("comp1").geom("geom1").feature("ca2").set("r", "r2_susp");
    model.component("comp1").geom("geom1").feature("ca2").set("angle1", -180);
    model.component("comp1").geom("geom1").feature("ca2").set("angle2", 0);
    model.component("comp1").geom("geom1").run("ca2");
    model.component("comp1").geom("geom1").create("ca3", "CircularArc");
    model.component("comp1").geom("geom1").feature("ca3")
         .set("center", new String[]{"d_voice_coil/2+3*r1_susp+2*r2_susp", "0"});
    model.component("comp1").geom("geom1").feature("ca3")
         .setIndex("center", "(-h_former+h_voice_coil/2+h_top_plate/2)*2/3-h_waveguide", 1);
    model.component("comp1").geom("geom1").feature("ca3").set("r", "r1_susp");
    model.component("comp1").geom("geom1").feature("ca3").set("angle2", 180);
    model.component("comp1").geom("geom1").run("ca3");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").set("ca1", "ca2", "ca3");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir1")
         .set("pos", new String[]{"0", "(-h_former+h_voice_coil/2+h_top_plate/2)/2 -h_waveguide"});
    model.component("comp1").geom("geom1").feature("mir1").set("axis", new int[]{0, 4});
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("pol2").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol2").set("type", "open");
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "r_model/4", 0, 1);
    model.component("comp1").geom("geom1").feature("pol2")
         .setIndex("table", "d_voice_coil/2+4*r1_susp+2*r2_susp+(d_waveguide/2-(d_voice_coil/2+4*r1_susp+2*r2_susp))", 1, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "r_model/4", 1, 1);
    model.component("comp1").geom("geom1").feature("pol2")
         .setIndex("table", "d_voice_coil/2+4*r1_susp+2*r2_susp+(d_waveguide/2-(d_voice_coil/2+4*r1_susp+2*r2_susp))", 2, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 0, 2, 1);
    model.component("comp1").geom("geom1").run("pol2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").label("\u591a\u5b54\u57df");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"d_porous/2", "h_porous"});
    model.component("comp1").geom("geom1").feature("r3")
         .set("pos", new String[]{"0", "-h_former+h_voice_coil/2+h_top_plate/2-h_waveguide"});
    model.component("comp1").geom("geom1").feature("r3").set("selresult", true);
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input")
         .set("c1", "ca1", "ca2", "ca3", "ic1", "ic2", "ls1", "ls2", "mir1", "pol1", "pol2", "r1", "r2", "r3");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("uni1", 1, 8, 15);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("ige1", "IgnoreEdges");
    model.component("comp1").geom("geom1").feature("ige1").selection("input").set("fin", 7);
    model.component("comp1").geom("geom1").run("ige1");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("PML \u57df");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("ige1", 5);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("\u97f3\u5708");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("ige1", 8);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("comsel1", "ComplementSelection");
    model.component("comp1").geom("geom1").feature("comsel1").set("input", new String[]{"sel2"});
    model.component("comp1").geom("geom1").feature("comsel1").label("\u7a7a\u6c14\u57df");
    model.component("comp1").geom("geom1").run("comsel1");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").label("\u7a84\u533a\u57df");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("ige1", 7, 11);
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("sel4", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel4").label("\u53ef\u89c6\u5316\u57df");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection")
         .set("ige1", 1, 2, 3, 6, 7, 9, 10, 11);
    model.component("comp1").geom("geom1").run("sel4");
    model.component("comp1").geom("geom1").create("sel5", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel5").label("\u5916\u573a");
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").set("ige1", 38);
    model.component("comp1").geom("geom1").run("sel5");
    model.component("comp1").geom("geom1").create("sel6", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel6").label("\u7f69");
    model.component("comp1").geom("geom1").feature("sel6").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel6").selection("selection").set("ige1", 40);
    model.component("comp1").geom("geom1").run("sel6");
    model.component("comp1").geom("geom1").create("sel7", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel7").label("\u5bf9\u79f0/\u8f8a\u652f\u627f\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("sel7").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel7").selection("selection").set("ige1", 3, 5);
    model.component("comp1").geom("geom1").run("sel7");
    model.component("comp1").geom("geom1").create("sel8", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel8").label("\u60ac\u67b6");
    model.component("comp1").geom("geom1").feature("sel8").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel8").selection("selection")
         .set("ige1", 41, 42, 43, 44, 45, 46);
    model.component("comp1").geom("geom1").run("sel8");
    model.component("comp1").geom("geom1").create("sel9", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel9").label("\u65cb\u8f6c\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("sel9").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel9").selection("selection")
         .set("ige1", 2, 4, 9, 10, 11, 12, 13, 15, 17, 18, 19, 20, 21, 22, 23, 26, 27, 28, 29, 30, 31, 32, 33, 34, 40, 41, 42, 43, 44, 45, 46, 47);
    model.component("comp1").geom("geom1").run("sel9");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").label("\u58f3\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("unisel1").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"ls1", "sel6", "sel8"});
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("sel10", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel10").label("\u6ce2\u5bfc");
    model.component("comp1").geom("geom1").feature("sel10").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel10").selection("selection").set("ige1", 47);
    model.component("comp1").geom("geom1").run("sel10");
    model.component("comp1").geom("geom1").create("sel11", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel11").label("\u56fa\u5b9a\u70b9");
    model.component("comp1").geom("geom1").feature("sel11").selection("selection").init(0);
    model.component("comp1").geom("geom1").feature("sel11").selection("selection").set("ige1", 31, 32);

    model.title(null);

    model.description("");

    model.label("tweeter_shape_optimization_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
