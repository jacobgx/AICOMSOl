/*
 * pipeline_corrosion_protection_iccp.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:42 by COMSOL 6.3.0.290. */
public class pipeline_corrosion_protection_iccp {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Corrosion_Module\\Cathodic_Protection");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("cp", "CathodicProtection", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/cp", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("ranode", "0.2[m]/2", "\u9633\u6781\u534a\u5f84");
    model.param().set("rpipe1", "6.625[in]/2", "\u7ba1\u9053 1 \u534a\u5f84");
    model.param().set("rpipe2", "16[in]/2", "\u7ba1\u9053 2 \u534a\u5f84");
    model.param().set("rpipe3", "10.75[in]/2", "\u7ba1\u9053 3 \u534a\u5f84");
    model.param().set("lanode", "10[m]", "\u9633\u6781\u957f\u5ea6");
    model.param().set("lpipe", "68.442[km]", "\u7ba1\u9053\u957f\u5ea6");
    model.param().set("sigma_top", "0.02[S/m]", "\u7535\u89e3\u8d28\u7535\u5bfc\u7387\uff0c\u9876\u5c42");
    model.param().set("sigma_bottom", "0.005[S/m]", "\u7535\u89e3\u8d28\u7535\u5bfc\u7387\uff0c\u5e95\u5c42");
    model.param().set("sigmas", "1/(1.74e-7[ohm*m])", "\u7535\u5bfc\u7387\uff0c\u7ba1\u9053");
    model.param().set("R_short", "1e-3[ohm]", "\u8fde\u63a5\u5904\u7684\u7535\u963b");
    model.param().set("E_control", "-0.7[V]", "\u63a7\u5236\u7535\u4f4d vs. CSE");

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"120[km]", "60[km]", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", 100, 2);
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"-25[km]", "-20[km]", "0"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("pos", -100, 2);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", 65, 0);
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").label("\u7ba1\u9053 1");
    model.component("comp1").geom("geom1").feature("wp1").set("quickz", -20);
    model.component("comp1").geom("geom1").feature("wp1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("wp1").set("selresultshow", "edg");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .set("table", new String[][]{{"0", "0"}, 
         {"2[km]", "2.5[km]"}, 
         {"7[km]", "1.5[km]"}, 
         {"8.6[km]", "2.5[km]"}, 
         {"16[km]", "7[km]"}, 
         {"18[km]", "10[km]"}, 
         {"21[km]", "11.5[km]"}, 
         {"26[km]", "14[km]"}, 
         {"30[km]", "14.5[km]"}, 
         {"31[km]", "17[km]"}, 
         {"39[km]", "20.3[km]"}, 
         {"42[km]", "21.5[km]"}, 
         {"45[km]", "19.6[km]"}, 
         {"47[km]", "18[km]"}, 
         {"53[km]", "20.5[km]"}, 
         {"lpipe", "19.5[km]"}});
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").label("\u7ba1\u9053 2");
    model.component("comp1").geom("geom1").feature("wp2").set("quickz", -15);
    model.component("comp1").geom("geom1").feature("wp2").set("selresult", true);
    model.component("comp1").geom("geom1").feature("wp2").set("selresultshow", "edg");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1")
         .set("table", new String[][]{{"0", "0+10[m]"}, 
         {"2[km]", "2.5[km]+10[m]"}, 
         {"7[km]", "1.5[km]+10[m]"}, 
         {"8.6[km]", "2.5[km]+10[m]"}, 
         {"16[km]", "7[km]+10[m]"}, 
         {"18[km]", "10[km]+10[m]"}, 
         {"21[km]", "11.5[km]+10[m]"}, 
         {"26[km]", "14[km]+10[m]"}, 
         {"30[km]", "14.5[km]+10[m]"}, 
         {"31[km]", "17[km]+10[m]"}, 
         {"39[km]", "20.3[km]+10[m]"}, 
         {"42[km]", "21.5[km]+10[m]"}, 
         {"45[km]", "19.6[km]+10[m]"}, 
         {"47[km]", "18[km]+10[m]"}, 
         {"53[km]", "20.5[km]+10[m]"}, 
         {"lpipe", "19.5[km]+10[m]"}});
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").create("wp3", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp3").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp3").label("\u7ba1\u9053 3");
    model.component("comp1").geom("geom1").feature("wp3").set("quickz", -15);
    model.component("comp1").geom("geom1").feature("wp3").set("selresult", true);
    model.component("comp1").geom("geom1").feature("wp3").set("selresultshow", "edg");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol1")
         .set("table", new String[][]{{"0", "0+20[m]"}, 
         {"2[km]", "2.5[km]+20[m]"}, 
         {"7[km]", "1.5[km]+20[m]"}, 
         {"8.6[km]", "2.5[km]+20[m]"}, 
         {"16[km]", "7[km]+20[m]"}, 
         {"18[km]", "10[km]+20[m]"}, 
         {"21[km]", "11.5[km]+20[m]"}, 
         {"26[km]", "14[km]+20[m]"}, 
         {"30[km]", "14.5[km]+20[m]"}, 
         {"31[km]", "17[km]+20[m]"}, 
         {"39[km]", "20.3[km]+20[m]"}, 
         {"42[km]", "21.5[km]+20[m]"}, 
         {"45[km]", "19.6[km]+20[m]"}, 
         {"47[km]", "18[km]+20[m]"}, 
         {"53[km]", "20.5[km]+20[m]"}, 
         {"lpipe", "19.5[km]+20[m]"}});
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").create("wp4", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp4").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp4").label("\u9633\u6781\u4f4d\u7f6e");
    model.component("comp1").geom("geom1").feature("wp4").set("quickz", -10);
    model.component("comp1").geom("geom1").feature("wp4").set("selresult", true);
    model.component("comp1").geom("geom1").feature("wp4").set("selresultshow", false);
    model.component("comp1").geom("geom1").feature("wp4").geom().create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pt1").setIndex("p", 100, 0);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pt1").setIndex("p", -200, 1);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pt1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pt1").set("selresultshow", "all");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature().duplicate("pt2", "pt1");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pt2").setIndex("p", "2[km]", 0);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pt2").setIndex("p", "3[km]-200", 1);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pt2").set("selresult", false);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature().duplicate("pt3", "pt2");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pt3").setIndex("p", "8.5[km]", 0);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pt3").setIndex("p", "2.8[km]+200", 1);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature().duplicate("pt4", "pt3");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pt4").setIndex("p", "16[km]", 0);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pt4").setIndex("p", "7[km]-200", 1);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature().duplicate("pt5", "pt4");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pt5").setIndex("p", "21[km]", 0);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pt5").setIndex("p", "11[km]+200", 1);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature().duplicate("pt6", "pt5");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pt6").setIndex("p", "30[km]", 0);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pt6").setIndex("p", "14.75[km]-200", 1);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature().duplicate("pt7", "pt6");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pt7").setIndex("p", "39[km]", 0);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pt7").setIndex("p", "20.5[km]+200", 1);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature().duplicate("pt8", "pt7");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pt8").setIndex("p", "45[km]", 0);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pt8").setIndex("p", "20[km]-200", 1);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature().duplicate("pt9", "pt8");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pt9").setIndex("p", "53[km]", 0);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pt9").setIndex("p", "20.5[km]+200", 1);
    model.component("comp1").geom("geom1").feature("wp4").geom().run("pt9");
    model.component("comp1").geom("geom1").run("wp4");
    model.component("comp1").geom("geom1").create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("copy1")
         .label("\u7528\u4e8e\u7f51\u683c\u5212\u5206\u7684\u7ba1\u9053 1 \u8f6e\u5ed3");
    model.component("comp1").geom("geom1").feature("copy1").selection("input").named("wp1");
    model.component("comp1").geom("geom1").feature("copy1").set("displz", 20);
    model.component("comp1").geom("geom1").feature("copy1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("copy1").set("selresultshow", false);
    model.component("comp1").geom("geom1").feature("copy1").set("propagatesel", false);
    model.component("comp1").geom("geom1").run("copy1");
    model.component("comp1").geom("geom1").create("copy2", "Copy");
    model.component("comp1").geom("geom1").feature("copy2").selection("input").named("wp2");
    model.component("comp1").geom("geom1").feature("copy2")
         .label("\u7528\u4e8e\u7f51\u683c\u5212\u5206\u7684\u7ba1\u9053 2 \u8f6e\u5ed3");
    model.component("comp1").geom("geom1").feature("copy2").set("displz", 15);
    model.component("comp1").geom("geom1").feature("copy2").set("selresult", true);
    model.component("comp1").geom("geom1").feature("copy2").set("propagatesel", false);
    model.component("comp1").geom("geom1").run("copy2");
    model.component("comp1").geom("geom1").create("copy3", "Copy");
    model.component("comp1").geom("geom1").feature("copy3").selection("input").named("wp3");
    model.component("comp1").geom("geom1").feature("copy3")
         .label("\u7528\u4e8e\u7f51\u683c\u5212\u5206\u7684\u7ba1\u9053 3 \u8f6e\u5ed3");
    model.component("comp1").geom("geom1").feature("copy3").set("displz", 15);
    model.component("comp1").geom("geom1").feature("copy3").set("selresult", true);
    model.component("comp1").geom("geom1").feature("copy3").set("selresultshow", false);
    model.component("comp1").geom("geom1").feature("copy3").set("propagatesel", false);
    model.component("comp1").geom("geom1").run("copy3");
    model.component("comp1").geom("geom1").create("copy4", "Copy");
    model.component("comp1").geom("geom1").feature("copy4")
         .label("\u7528\u4e8e\u7f51\u683c\u5212\u5206\u7684\u9633\u6781\u4f4d\u7f6e");
    model.component("comp1").geom("geom1").feature("copy4").selection("input").named("wp4");
    model.component("comp1").geom("geom1").feature("copy4").set("displz", 10);
    model.component("comp1").geom("geom1").feature("copy4").set("selresult", true);
    model.component("comp1").geom("geom1").feature("copy4").set("selresultshow", "pnt");
    model.component("comp1").geom("geom1").feature("copy4").set("propagatesel", false);
    model.component("comp1").geom("geom1").run("copy4");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("blk1");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", 15);
    model.component("comp1").geom("geom1").feature("rot1").set("pos", new String[]{"30[km]", "10[km]", "0"});
    model.component("comp1").geom("geom1").run("rot1");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1")
         .label("\u7528\u4e8e\u7f51\u683c\u5212\u5206\u7684\u7ba1\u9053\u8f6e\u5ed3");
    model.component("comp1").geom("geom1").feature("unisel1").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"copy1", "copy2", "copy3"});
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u9633\u6781 1");
    model.component("comp1").selection("sel1").geom(0);
    model.component("comp1").selection("sel1").set(13);
    model.component("comp1").selection().duplicate("sel2", "sel1");
    model.component("comp1").selection("sel2").label("\u9633\u6781 2");
    model.component("comp1").selection("sel2").set(21);
    model.component("comp1").selection().duplicate("sel3", "sel2");
    model.component("comp1").selection("sel3").label("\u9633\u6781 3");
    model.component("comp1").selection("sel3").set(29);
    model.component("comp1").selection().duplicate("sel4", "sel3");
    model.component("comp1").selection("sel4").label("\u9633\u6781 4");
    model.component("comp1").selection("sel4").set(37);
    model.component("comp1").selection().duplicate("sel5", "sel4");
    model.component("comp1").selection("sel5").label("\u9633\u6781 5");
    model.component("comp1").selection("sel5").set(51);
    model.component("comp1").selection().duplicate("sel6", "sel5");
    model.component("comp1").selection("sel6").label("\u9633\u6781 6");
    model.component("comp1").selection("sel6").set(71);
    model.component("comp1").selection().duplicate("sel7", "sel6");
    model.component("comp1").selection("sel7").label("\u9633\u6781 7");
    model.component("comp1").selection("sel7").set(85);
    model.component("comp1").selection().duplicate("sel8", "sel7");
    model.component("comp1").selection("sel8").label("\u9633\u6781 8");
    model.component("comp1").selection("sel8").set(99);
    model.component("comp1").selection().duplicate("sel9", "sel8");
    model.component("comp1").selection("sel9").label("\u9633\u6781 9");
    model.component("comp1").selection("sel9").set(113);
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u7ba1\u9053");
    model.component("comp1").selection("uni1").set("entitydim", 1);
    model.component("comp1").selection("uni1")
         .set("input", new String[]{"geom1_wp1_edg", "geom1_wp2_edg", "geom1_wp3_edg"});

    model.component("comp1").physics("cp").prop("PhysicsVsMaterialsReferenceElectrodePotential")
         .set("PhysicsVsMaterialsReferenceElectrodePotential", "CSE");
    model.component("comp1").physics("cp").prop("ShapeProperty").set("order_electricpotentialionicphase", 1);
    model.component("comp1").physics("cp").feature("ice1").set("sigmal_mat", "userdef");
    model.component("comp1").physics("cp").feature("ice1")
         .set("sigmal", new String[]{"sigma_top", "0", "0", "0", "sigma_top", "0", "0", "0", "sigma_top"});
    model.component("comp1").physics("cp").create("ice2", "Electrolyte", 3);
    model.component("comp1").physics("cp").feature("ice2").selection().set(1);
    model.component("comp1").physics("cp").feature("ice2").set("sigmal_mat", "userdef");
    model.component("comp1").physics("cp").feature("ice2")
         .set("sigmal", new String[]{"sigma_bottom", "0", "0", "0", "sigma_bottom", "0", "0", "0", "sigma_bottom"});
    model.component("comp1").physics("cp").create("edge1", "EdgeElectrode", 1);
    model.component("comp1").physics("cp").feature("edge1").selection().named("geom1_wp1_edg");
    model.component("comp1").physics("cp").feature("edge1").set("redge", "rpipe1");
    model.component("comp1").physics("cp").feature("edge1").feature("er1").set("Eeq_mat", "from_mat");
    model.component("comp1").physics("cp").feature("edge1").feature("er1").set("ilocmat_mat", "from_mat");
    model.component("comp1").physics("cp").feature("edge1").create("connp1", "EdgeConnectionPoint", 0);
    model.component("comp1").physics("cp").feature("edge1").feature("connp1").selection().set(7);
    model.component("comp1").physics("cp").feature("edge1").feature("connp1").set("DefineReferenceElectrode", true);
    model.component("comp1").physics("cp").feature("edge1").feature().duplicate("connp2", "connp1");
    model.component("comp1").physics("cp").feature("edge1").feature("connp2").selection().set(15);
    model.component("comp1").physics("cp").feature("edge1").feature().duplicate("connp3", "connp2");
    model.component("comp1").physics("cp").feature("edge1").feature("connp3").selection().set(31);
    model.component("comp1").physics("cp").feature("edge1").feature().duplicate("connp4", "connp3");
    model.component("comp1").physics("cp").feature("edge1").feature("connp4").selection().set(39);
    model.component("comp1").physics("cp").feature("edge1").feature().duplicate("connp5", "connp4");
    model.component("comp1").physics("cp").feature("edge1").feature("connp5").selection().set(53);
    model.component("comp1").physics("cp").feature("edge1").feature().duplicate("connp6", "connp5");
    model.component("comp1").physics("cp").feature("edge1").feature("connp6").selection().set(65);
    model.component("comp1").physics("cp").feature("edge1").feature().duplicate("connp7", "connp6");
    model.component("comp1").physics("cp").feature("edge1").feature("connp7").selection().set(79);
    model.component("comp1").physics("cp").feature("edge1").feature().duplicate("connp8", "connp7");
    model.component("comp1").physics("cp").feature("edge1").feature("connp8").selection().set(93);
    model.component("comp1").physics("cp").feature("edge1").feature().duplicate("connp9", "connp8");
    model.component("comp1").physics("cp").feature("edge1").feature("connp9").selection().set(107);
    model.component("comp1").physics("cp").create("edge2", "EdgeElectrode", 1);
    model.component("comp1").physics("cp").feature("edge2").selection().named("geom1_wp2_edg");
    model.component("comp1").physics("cp").feature("edge2").set("redge", "rpipe2");
    model.component("comp1").physics("cp").feature("edge2").feature("er1").set("Eeq_mat", "from_mat");
    model.component("comp1").physics("cp").feature("edge2").feature("er1").set("ilocmat_mat", "from_mat");
    model.component("comp1").physics("cp").feature("edge2").create("extshort1", "EdgeExternalShort", 0);
    model.component("comp1").physics("cp").feature("edge2").feature("extshort1").selection().set(9);
    model.component("comp1").physics("cp").feature("edge2").feature("extshort1").set("R", "R_short");
    model.component("comp1").physics("cp").feature("edge2").feature("extshort1")
         .set("phisthere_src", "root.comp1.cp.phisconnect_edge1_connp1");
    model.component("comp1").physics("cp").feature("edge2").create("extshort2", "EdgeExternalShort", 0);
    model.component("comp1").physics("cp").feature("edge2").feature("extshort2").selection().set(17);
    model.component("comp1").physics("cp").feature("edge2").feature("extshort2").set("R", "R_short");
    model.component("comp1").physics("cp").feature("edge2").feature("extshort2")
         .set("phisthere_src", "root.comp1.cp.phisconnect_edge1_connp2");
    model.component("comp1").physics("cp").feature("edge2").create("extshort3", "EdgeExternalShort", 0);
    model.component("comp1").physics("cp").feature("edge2").feature("extshort3").selection().set(33);
    model.component("comp1").physics("cp").feature("edge2").feature("extshort3").set("R", "R_short");
    model.component("comp1").physics("cp").feature("edge2").feature("extshort3")
         .set("phisthere_src", "root.comp1.cp.phisconnect_edge1_connp3");
    model.component("comp1").physics("cp").feature("edge2").create("extshort4", "EdgeExternalShort", 0);
    model.component("comp1").physics("cp").feature("edge2").feature("extshort4").selection().set(41);
    model.component("comp1").physics("cp").feature("edge2").feature("extshort4").set("R", "R_short");
    model.component("comp1").physics("cp").feature("edge2").feature("extshort4")
         .set("phisthere_src", "root.comp1.cp.phisconnect_edge1_connp4");
    model.component("comp1").physics("cp").feature("edge2").create("extshort5", "EdgeExternalShort", 0);
    model.component("comp1").physics("cp").feature("edge2").feature("extshort5").selection().set(55);
    model.component("comp1").physics("cp").feature("edge2").feature("extshort5").set("R", "R_short");
    model.component("comp1").physics("cp").feature("edge2").feature("extshort5")
         .set("phisthere_src", "root.comp1.cp.phisconnect_edge1_connp5");
    model.component("comp1").physics("cp").feature("edge2").create("extshort6", "EdgeExternalShort", 0);
    model.component("comp1").physics("cp").feature("edge2").feature("extshort6").selection().set(67);
    model.component("comp1").physics("cp").feature("edge2").feature("extshort6").set("R", "R_short");
    model.component("comp1").physics("cp").feature("edge2").feature("extshort6")
         .set("phisthere_src", "root.comp1.cp.phisconnect_edge1_connp6");
    model.component("comp1").physics("cp").feature("edge2").create("extshort7", "EdgeExternalShort", 0);
    model.component("comp1").physics("cp").feature("edge2").feature("extshort7").selection().set(81);
    model.component("comp1").physics("cp").feature("edge2").feature("extshort7").set("R", "R_short");
    model.component("comp1").physics("cp").feature("edge2").feature("extshort7")
         .set("phisthere_src", "root.comp1.cp.phisconnect_edge1_connp7");
    model.component("comp1").physics("cp").feature("edge2").create("extshort8", "EdgeExternalShort", 0);
    model.component("comp1").physics("cp").feature("edge2").feature("extshort8").selection().set(95);
    model.component("comp1").physics("cp").feature("edge2").feature("extshort8").set("R", "R_short");
    model.component("comp1").physics("cp").feature("edge2").feature("extshort8")
         .set("phisthere_src", "root.comp1.cp.phisconnect_edge1_connp8");
    model.component("comp1").physics("cp").feature("edge2").create("extshort9", "EdgeExternalShort", 0);
    model.component("comp1").physics("cp").feature("edge2").feature("extshort9").selection().set(109);
    model.component("comp1").physics("cp").feature("edge2").feature("extshort9").set("R", "R_short");
    model.component("comp1").physics("cp").feature("edge2").feature("extshort9")
         .set("phisthere_src", "root.comp1.cp.phisconnect_edge1_connp9");
    model.component("comp1").physics("cp").feature().duplicate("edge3", "edge2");
    model.component("comp1").physics("cp").feature("edge3").selection().named("geom1_wp3_edg");
    model.component("comp1").physics("cp").feature("edge3").set("redge", "rpipe3");
    model.component("comp1").physics("cp").feature("edge3").feature("extshort1").selection().set(11);
    model.component("comp1").physics("cp").feature("edge3").feature("extshort2").selection().set(19);
    model.component("comp1").physics("cp").feature("edge3").feature("extshort3").selection().set(35);
    model.component("comp1").physics("cp").feature("edge3").feature("extshort4").selection().set(43);
    model.component("comp1").physics("cp").feature("edge3").feature("extshort5").selection().set(57);
    model.component("comp1").physics("cp").feature("edge3").feature("extshort6").selection().set(69);
    model.component("comp1").physics("cp").feature("edge3").feature("extshort7").selection().set(83);
    model.component("comp1").physics("cp").feature("edge3").feature("extshort8").selection().set(97);
    model.component("comp1").physics("cp").feature("edge3").feature("extshort9").selection().set(111);
    model.component("comp1").physics("cp").feature("edge3").create("egnd1", "EdgeElectricGround", 0);
    model.component("comp1").physics("cp").feature("edge3").feature("egnd1").selection().set(119);
    model.component("comp1").physics("cp").create("imprcp1", "ImpressedCurrentPoint", 0);
    model.component("comp1").physics("cp").feature("imprcp1").selection().named("sel1");
    model.component("comp1").physics("cp").feature("imprcp1").set("Eimpr", "E_control");
    model.component("comp1").physics("cp").feature("imprcp1")
         .set("phissense_src", "root.comp1.cp.phisconnect_edge1_connp1");
    model.component("comp1").physics("cp").feature("imprcp1").set("phisref_src", "root.comp1.cp.phisref_connp1");
    model.component("comp1").physics("cp").feature().duplicate("imprcp2", "imprcp1");
    model.component("comp1").physics("cp").feature("imprcp2").selection().named("sel2");
    model.component("comp1").physics("cp").feature("imprcp2")
         .set("phissense_src", "root.comp1.cp.phisconnect_edge1_connp2");
    model.component("comp1").physics("cp").feature("imprcp2").set("phisref_src", "root.comp1.cp.phisref_connp2");
    model.component("comp1").physics("cp").feature().duplicate("imprcp3", "imprcp2");
    model.component("comp1").physics("cp").feature("imprcp3").selection().named("sel3");
    model.component("comp1").physics("cp").feature("imprcp3")
         .set("phissense_src", "root.comp1.cp.phisconnect_edge1_connp3");
    model.component("comp1").physics("cp").feature("imprcp3").set("phisref_src", "root.comp1.cp.phisref_connp3");
    model.component("comp1").physics("cp").feature().duplicate("imprcp4", "imprcp3");
    model.component("comp1").physics("cp").feature("imprcp4").selection().named("sel4");
    model.component("comp1").physics("cp").feature("imprcp4")
         .set("phissense_src", "root.comp1.cp.phisconnect_edge1_connp4");
    model.component("comp1").physics("cp").feature("imprcp4").set("phisref_src", "root.comp1.cp.phisref_connp4");
    model.component("comp1").physics("cp").feature().duplicate("imprcp5", "imprcp4");
    model.component("comp1").physics("cp").feature("imprcp5").selection().named("sel5");
    model.component("comp1").physics("cp").feature("imprcp5")
         .set("phissense_src", "root.comp1.cp.phisconnect_edge1_connp5");
    model.component("comp1").physics("cp").feature("imprcp5").set("phisref_src", "root.comp1.cp.phisref_connp5");
    model.component("comp1").physics("cp").feature().duplicate("imprcp6", "imprcp5");
    model.component("comp1").physics("cp").feature("imprcp6").selection().named("sel6");
    model.component("comp1").physics("cp").feature("imprcp6")
         .set("phissense_src", "root.comp1.cp.phisconnect_edge1_connp6");
    model.component("comp1").physics("cp").feature("imprcp6").set("phisref_src", "root.comp1.cp.phisref_connp6");
    model.component("comp1").physics("cp").feature().duplicate("imprcp7", "imprcp6");
    model.component("comp1").physics("cp").feature("imprcp7").selection().named("sel7");
    model.component("comp1").physics("cp").feature("imprcp7")
         .set("phissense_src", "root.comp1.cp.phisconnect_edge1_connp7");
    model.component("comp1").physics("cp").feature("imprcp7").set("phisref_src", "root.comp1.cp.phisref_connp7");
    model.component("comp1").physics("cp").feature().duplicate("imprcp8", "imprcp7");
    model.component("comp1").physics("cp").feature("imprcp8").selection().named("sel8");
    model.component("comp1").physics("cp").feature("imprcp8")
         .set("phissense_src", "root.comp1.cp.phisconnect_edge1_connp8");
    model.component("comp1").physics("cp").feature("imprcp8").set("phisref_src", "root.comp1.cp.phisref_connp8");
    model.component("comp1").physics("cp").feature().duplicate("imprcp9", "imprcp8");
    model.component("comp1").physics("cp").feature("imprcp9").selection().named("sel9");
    model.component("comp1").physics("cp").feature("imprcp9")
         .set("phissense_src", "root.comp1.cp.phisconnect_edge1_connp9");
    model.component("comp1").physics("cp").feature("imprcp9").set("phisref_src", "root.comp1.cp.phisref_connp9");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("LocalCurrentDensity", "LocalCurrentDensity", "Local current density");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElectrodePotential", "ElectrodePotential", "Equilibrium potential");
    model.component("comp1").material("mat1").label("Q235 steel in soil");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").label("Local current density");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").func("int1")
         .set("funcname", "iloc_exp");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").func("int1")
         .set("table", new String[][]{{"-0.425", "3e-3"}, 
         {"-0.56", "0"}, 
         {"-0.72", "-18e-3"}, 
         {"-0.95", "-66e-3"}, 
         {"-1.1", "-90e-3"}, 
         {"-1.14", "-105e-3"}, 
         {"-1.18", "-126e-3"}});
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").func("int1")
         .set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").func("int1")
         .set("fununit", new String[]{"A/m^2"});
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").func("int1")
         .set("argunit", new String[]{"V"});
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity")
         .set("ilocmat", "iloc_exp(E_vs_ref_exp)");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity")
         .setPropertyInfo("ilocmat", "G. Cui, Z. Li, C. Yang and M. Wang, \u201cThe influence of DC stray current on pipeline corrosion\u201d, Petroleum Science, vol. 13, p. 135, 2016.");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").set("E_vs_SHE", "V");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity")
         .descr("E_vs_SHE", "\u7535\u6781\u7535\u4f4d vs. SHE");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity")
         .set("E_ref_exp_vs_SHE", "0.314 [V]");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity")
         .descr("E_ref_exp_vs_SHE", "\u5b9e\u9a8c\u4e2d\u7684\u53c2\u6bd4\u7535\u6781\u7535\u4f4d vs. SHE");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity")
         .set("E_vs_ref_exp", "E_vs_SHE-E_ref_exp_vs_SHE");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity")
         .descr("E_vs_ref_exp", "\u7535\u6781\u7535\u4f4d vs. \u5b9e\u9a8c CSE \u53c2\u6bd4\u7535\u6781");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").addInput("electricpotential");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").label("Equilibrium potential");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").set("Eeq", "Eeq_vs_SHE");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential")
         .setPropertyInfo("Eeq", "G. Cui, Z. Li, C. Yang and M. Wang, \u201cThe influence of DC stray current on pipeline corrosion\u201d, Petroleum Science, vol. 13, p. 135, 2016.");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").set("dEeqdT", "0[V/K]");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").set("cEeqref", "0[mol/m^3]");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").set("Eeq_vs_ref_exp", "-0.56 [V]");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential")
         .descr("Eeq_vs_ref_exp", "\u5e73\u8861\uff08\u5f00\u8def\uff09\u7535\u4f4d vs. CSE");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential")
         .set("E_ref_exp_vs_SHE", "0.314 [V]");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential")
         .descr("E_ref_exp_vs_SHE", "\u5b9e\u9a8c\u4e2d\u7684\u53c2\u6bd4\u7535\u6781\u7535\u4f4d vs. SHE");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential")
         .set("Eeq_vs_SHE", "Eeq_vs_ref_exp+E_ref_exp_vs_SHE");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential")
         .descr("Eeq_vs_SHE", "\u5e73\u8861\uff08\u5f00\u8def\uff09\u7535\u4f4d vs. SHE");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").addInput("concentration");
    model.component("comp1").material("mat1").selection().geom("geom1", 1);
    model.component("comp1").material("mat1").selection().named("uni1");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"sigmas"});

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(7);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 0);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().named("geom1_copy4_pnt");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "1[m]");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().named("geom1_unisel1");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmax", 50);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmin", 25);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size3", "Size");
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set(7);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().named("geom1_unisel1");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 1);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhmin", 1);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("swe1").selection("sourceface").set(7);
    model.component("comp1").mesh("mesh1").feature("swe1").selection("targetface").set(6);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("type", "explicit");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1")
         .set("explicit", "0, 10/35, 15/35, 20/35,  1");
    model.component("comp1").mesh("mesh1").create("swe2", "Sweep");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").label("\u7535\u89e3\u8d28\u7535\u4f4d (cp)");
    model.result("pg1").create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("expr", new String[]{"phil"});
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("expr", new String[]{"cp.Ilx", "cp.Ily", "cp.Ilz"});
    model.result("pg1").feature("str1").set("posmethod", "start");
    model.result("pg1").feature("str1").set("pointtype", "arrow");
    model.result("pg1").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg1").feature("str1").set("color", "gray");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").label("\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6 (cp)");
    model.result("pg2").create("str1", "Streamline");
    model.result("pg2").feature("str1").set("expr", new String[]{"cp.Ilx", "cp.Ily", "cp.Ilz"});
    model.result("pg2").feature("str1").set("posmethod", "start");
    model.result("pg2").feature("str1").set("pointtype", "arrow");
    model.result("pg2").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg2").feature("str1").set("color", "gray");
    model.result("pg2").feature("str1").create("col1", "Color");
    model.result("pg2").feature("str1").feature("col1").set("expr", "root.comp1.cp.IlMag");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").label("\u7535\u6781\u7535\u4f4d vs. \u76f8\u90bb\u53c2\u6bd4\u7535\u4f4d (cp)");
    model.result("pg3").create("str1", "Streamline");
    model.result("pg3").feature("str1").set("expr", new String[]{"cp.Ilx", "cp.Ily", "cp.Ilz"});
    model.result("pg3").feature("str1").set("posmethod", "start");
    model.result("pg3").feature("str1").set("pointtype", "arrow");
    model.result("pg3").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg3").feature("str1").set("color", "gray");
    model.result("pg3").create("line1", "Line");
    model.result("pg3").feature("line1").set("expr", new String[]{"cp.Evsref"});
    model.result("pg3").feature("line1").set("linetype", "tube");
    model.result("pg3").feature("line1").set("radiusexpr", "root.comp1.cp.redge");
    model.result("pg3").feature("line1").set("tuberadiusscaleactive", "on");
    model.result("pg3").feature("line1").set("tuberadiusscale", "1");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("str1").set("number", 100);
    model.result("pg1").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u7535\u6781\u7535\u4f4d vs. \u76f8\u90bb\u53c2\u6bd4\u7535\u4f4d");
    model.result("pg4").set("axislimits", true);
    model.result("pg4").set("xmin", 0);
    model.result("pg4").set("xmax", 75000);
    model.result("pg4").set("ymin", -0.75);
    model.result("pg4").set("ymax", -0.5);
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr1").set("linewidth", "preference");
    model.result("pg4").feature("lngr1").selection().named("geom1_wp1_edg");
    model.result("pg4").feature("lngr1").set("expr", "cp.Evsref");
    model.result("pg4").feature("lngr1")
         .set("descr", "\u7535\u6781\u7535\u4f4d vs. \u76f8\u90bb\u53c2\u6bd4\u7535\u4f4d");
    model.result("pg4").feature("lngr1").set("linestyle", "cycle");
    model.result("pg4").feature("lngr1").set("linewidth", 2);
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").feature("lngr1").set("legendmethod", "manual");
    model.result("pg4").feature("lngr1").setIndex("legends", "\u7ba1\u9053 1", 0);
    model.result("pg4").feature().duplicate("lngr2", "lngr1");
    model.result("pg4").run();
    model.result("pg4").feature("lngr2").selection().named("geom1_wp2_edg");
    model.result("pg4").feature("lngr2").set("titletype", "none");
    model.result("pg4").feature("lngr2").setIndex("legends", "\u7ba1\u9053 2", 0);
    model.result("pg4").feature().duplicate("lngr3", "lngr2");
    model.result("pg4").run();
    model.result("pg4").feature("lngr3").selection().named("geom1_wp3_edg");
    model.result("pg4").feature("lngr3").setIndex("legends", "\u7ba1\u9053 3", 0);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u603b\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg5").set("ymin", -0.03);
    model.result("pg5").set("ymax", 0.01);
    model.result("pg5").run();
    model.result("pg5").feature("lngr1").set("expr", "cp.itot");
    model.result("pg5").feature("lngr1").set("descr", "\u754c\u9762\u603b\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg5").run();
    model.result("pg5").feature("lngr2").set("expr", "cp.itot");
    model.result("pg5").feature("lngr2").set("descr", "\u754c\u9762\u603b\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg5").run();
    model.result("pg5").feature("lngr3").set("expr", "cp.itot");
    model.result("pg5").feature("lngr3").set("descr", "\u754c\u9762\u603b\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1")
         .set("expr", new String[]{"comp1.cp.Itotimpr_imprcp1", "comp1.cp.Itotimpr_imprcp2", "comp1.cp.Itotimpr_imprcp3", "comp1.cp.Itotimpr_imprcp4", "comp1.cp.Itotimpr_imprcp5", "comp1.cp.Itotimpr_imprcp6", "comp1.cp.Itotimpr_imprcp7", "comp1.cp.Itotimpr_imprcp8", "comp1.cp.Itotimpr_imprcp9"});
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u5916\u52a0\u7535\u6d41");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "\u9633\u6781\u7f16\u53f7");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "\u7535\u6d41 (A)");
    model.result("pg6").create("tblp1", "Table");
    model.result("pg6").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg6").feature("tblp1").set("linewidth", "preference");
    model.result("pg6").feature("tblp1").set("rowbased", true);
    model.result("pg6").feature("tblp1").set("xaxisdata", "rowindex");
    model.result("pg6").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg6").feature("tblp1").set("plotcolumns", new int[]{1});
    model.result("pg6").feature("tblp1").set("linewidth", 2);
    model.result("pg6").run();
    model.result("pg2").run();
    model.result().remove("pg2");
    model.result().remove("pg3");
    model.result("pg4").run();

    model
         .title("\u5916\u52a0\u7535\u6d41\u9634\u6781\u4fdd\u62a4\u5728\u7ba1\u9053\u8150\u8680\u9632\u62a4\u4e2d\u7684\u5e94\u7528");

    model
         .description("\u672c\u4f8b\u901a\u8fc7\u4f7f\u7528\u4e5d\u4e2a\u72ec\u7acb\u9633\u6781\u7684\u5916\u52a0\u7535\u6d41\u9634\u6781\u4fdd\u62a4 (ICCP) \u7cfb\u7edf\u6765\u4fdd\u62a4\u4e09\u4e2a\u5e76\u884c\u7ba1\u9053\u514d\u53d7\u8150\u8680\u3002\n\n\u5176\u4e2d\u901a\u8fc7\u8ba1\u7b97\u6cbf\u7ba1\u9053\u7684\u7535\u6781\u7535\u4f4d\u548c\u754c\u9762\u603b\u7535\u6d41\u5bc6\u5ea6\u6765\u8bc4\u4f30\u8150\u8680\u9632\u62a4\u7cfb\u7edf\u7684\u6027\u80fd\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("pipeline_corrosion_protection_iccp.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
