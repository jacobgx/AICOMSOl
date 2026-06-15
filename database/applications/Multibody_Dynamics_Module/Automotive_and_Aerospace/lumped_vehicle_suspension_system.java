/*
 * lumped_vehicle_suspension_system.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:20 by COMSOL 6.3.0.290. */
public class lumped_vehicle_suspension_system {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Multibody_Dynamics_Module\\Automotive_and_Aerospace");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("lms", "LumpedMechanicalSystem", "geom1");
    model.component("comp1").physics().create("mbd", "MultibodyDynamics", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/lms", true);
    model.study("std1").feature("time").setSolveFor("/physics/mbd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("m_body", "670[kg]", "\u8f66\u8eab\u8d28\u91cf");
    model.param().set("I_roll", "800[kg*m^2]", "\u6a2a\u6447\u60ef\u6027");
    model.param().set("I_pitch", "1100[kg*m^2]", "\u4fef\u4ef0\u60ef\u6027");
    model.param().set("m_wh", "30[kg]", "\u8f66\u8f6e\u8d28\u91cf");
    model.param().set("m_p", "120[kg]", "\u4e58\u5ba2\u8d28\u91cf");
    model.param().set("k_wh", "175500[N/m]", "\u8f66\u8f6e\u521a\u5ea6");
    model.param().set("k_sus", "17500[N/m]", "\u60ac\u6302\u5f39\u7c27\u521a\u5ea6");
    model.param().set("k_seat", "1750[N/m]", "\u5ea7\u6905\u5f39\u7c27\u521a\u5ea6");
    model.param().set("c_sus", "1460[N*s/m]", "\u60ac\u6302\u963b\u5c3c\u5668\u9ecf\u5ea6");
    model.param().set("c_seat", "700[N*s/m]", "\u5ea7\u6905\u963b\u5c3c\u5668\u9ecf\u5ea6");
    model.param().set("r_wb", "1.9975[m]", "\u8f74\u8ddd");
    model.param().set("r_tw", "0.8025[m]", "\u8f6e\u8ddd");
    model.param().set("hb", "4[cm]", "\u51f8\u8d77\u9ad8\u5ea6");
    model.param().set("wb", "7.5[cm]", "\u51f8\u8d77\u5bbd\u5ea6");
    model.param().set("speed", "40[km/h]", "\u8f66\u901f");
    model.param().set("tb", "4*wb/speed", "\u51f8\u8d77\u65f6\u95f4\u5468\u671f");
    model.param().set("td", "r_wb/speed", "\u524d\u8f6e\u4e0e\u540e\u8f6e\u4e4b\u95f4\u7684\u5ef6\u8fdf");

    model.func().create("wv1", "Wave");
    model.func("wv1").set("type", "square");
    model.func("wv1").set("smooth", "tb/10");
    model.func("wv1").set("dutycycle", 0.25);
    model.func("wv1").set("period", "tb");
    model.func("wv1").set("amplitude", "hb/2");
    model.func().duplicate("wv2", "wv1");
    model.func("wv2").set("phase", "-2*pi/tb*td");

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"2*r_wb", "2*r_tw", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "r_wb/20", 2);
    model.component("comp1").geom("geom1").feature("blk1").set("base", "center");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("lms").feature("fix1").active(false);
    model.component("comp1").physics("lms").create("sub1", "SubSystemBlock", -1);
    model.component("comp1").physics("lms").feature("sub1")
         .label("\u5b50\u7cfb\u7edf\u5b9a\u4e49\uff1a\u8f66\u8f6e");
    model.component("comp1").physics("lms").feature("sub1").create("K1", "Spring", -1);
    model.component("comp1").physics("lms").feature("sub1").feature("K1").setIndex("Connections", "a", 0, 0);
    model.component("comp1").physics("lms").feature("sub1").feature("K1").set("k", "k_wh");
    model.component("comp1").physics("lms").feature("sub1").create("M1", "Mass", -1);
    model.component("comp1").physics("lms").feature("sub1").feature("M1").setIndex("Connections", 1, 0, 0);
    model.component("comp1").physics("lms").feature("sub1").feature("M1").set("m", "m_wh");
    model.component("comp1").physics("lms").feature("sub1").create("K2", "Spring", -1);
    model.component("comp1").physics("lms").feature("sub1").feature("K2").setIndex("Connections", 2, 0, 0);
    model.component("comp1").physics("lms").feature("sub1").feature("K2").setIndex("Connections", "b", 1, 0);
    model.component("comp1").physics("lms").feature("sub1").feature("K2").set("k", "k_sus");
    model.component("comp1").physics("lms").feature("sub1").create("C1", "Damper", -1);
    model.component("comp1").physics("lms").feature("sub1").feature("C1").setIndex("Connections", 2, 0, 0);
    model.component("comp1").physics("lms").feature("sub1").feature("C1").setIndex("Connections", "b", 1, 0);
    model.component("comp1").physics("lms").feature("sub1").feature("C1").set("c", "c_sus");
    model.component("comp1").physics("lms").create("sub2", "SubSystemBlock", -1);
    model.component("comp1").physics("lms").feature("sub2")
         .label("\u5b50\u7cfb\u7edf\u5b9a\u4e49\uff1a\u5ea7\u6905");
    model.component("comp1").physics("lms").feature("sub2").set("Connections", new String[][]{{"a"}});
    model.component("comp1").physics("lms").feature("sub2").create("K1", "Spring", -1);
    model.component("comp1").physics("lms").feature("sub2").feature("K1").setIndex("Connections", "a", 0, 0);
    model.component("comp1").physics("lms").feature("sub2").feature("K1").set("k", "k_seat");
    model.component("comp1").physics("lms").feature("sub2").create("C1", "Damper", -1);
    model.component("comp1").physics("lms").feature("sub2").feature("C1").setIndex("Connections", "a", 0, 0);
    model.component("comp1").physics("lms").feature("sub2").feature("C1").setIndex("Connections", 1, 1, 0);
    model.component("comp1").physics("lms").feature("sub2").feature("C1").set("c", "c_seat");
    model.component("comp1").physics("lms").feature("sub2").create("mn1", "MassNode", -1);
    model.component("comp1").physics("lms").feature("sub2").feature("mn1").tag("m1");
    model.component("comp1").physics("lms").feature("sub2").feature("m1").setIndex("Connections", 1, 0, 0);
    model.component("comp1").physics("lms").feature("sub2").feature("m1").set("m", "m_p");
    model.component("comp1").physics("lms").create("disp1", "DisplacementNode", -1);
    model.component("comp1").physics("lms").feature("disp1").label("\u4f4d\u79fb\u8282\u70b9\uff1a\u5de6\u524d");
    model.component("comp1").physics("lms").feature("disp1").set("up1", "hb/2+wv1(t[1/s])");
    model.component("comp1").physics("lms").create("disp2", "DisplacementNode", -1);
    model.component("comp1").physics("lms").feature("disp2").label("\u4f4d\u79fb\u8282\u70b9\uff1a\u53f3\u524d");
    model.component("comp1").physics("lms").feature("disp2").set("up1", "0[mm]");
    model.component("comp1").physics("lms").create("disp3", "DisplacementNode", -1);
    model.component("comp1").physics("lms").feature("disp3").label("\u4f4d\u79fb\u8282\u70b9\uff1a\u5de6\u540e");
    model.component("comp1").physics("lms").feature("disp3").set("up1", "hb/2+wv2(t[1/s])");
    model.component("comp1").physics("lms").create("disp4", "DisplacementNode", -1);
    model.component("comp1").physics("lms").feature("disp4").label("\u4f4d\u79fb\u8282\u70b9\uff1a\u53f3\u540e");
    model.component("comp1").physics("lms").feature("disp4").set("up1", "0[mm]");
    model.component("comp1").physics("lms").create("X1", "SubSystem", -1);
    model.component("comp1").physics("lms").feature("X1")
         .label("\u5b50\u7cfb\u7edf\u5b9e\u4f8b X1\uff1a\u5de6\u524d\u8f6e");
    model.component("comp1").physics("lms").feature("X1").set("subSystemName", "sub1");
    model.component("comp1").physics("lms").feature("X1").setIndex("Connections", 1, 0, 0);
    model.component("comp1").physics("lms").feature("X1").setIndex("Connections", 5, 1, 0);
    model.component("comp1").physics("lms").create("X2", "SubSystem", -1);
    model.component("comp1").physics("lms").feature("X2")
         .label("\u5b50\u7cfb\u7edf\u5b9e\u4f8b X2\uff1a\u53f3\u524d\u8f6e");
    model.component("comp1").physics("lms").feature("X2").set("subSystemName", "sub1");
    model.component("comp1").physics("lms").feature("X2").setIndex("Connections", 2, 0, 0);
    model.component("comp1").physics("lms").feature("X2").setIndex("Connections", 6, 1, 0);
    model.component("comp1").physics("lms").create("X3", "SubSystem", -1);
    model.component("comp1").physics("lms").feature("X3")
         .label("\u5b50\u7cfb\u7edf\u5b9e\u4f8b X3\uff1a\u5de6\u540e\u8f6e");
    model.component("comp1").physics("lms").feature("X3").set("subSystemName", "sub1");
    model.component("comp1").physics("lms").feature("X3").setIndex("Connections", 3, 0, 0);
    model.component("comp1").physics("lms").feature("X3").setIndex("Connections", 7, 1, 0);
    model.component("comp1").physics("lms").create("X4", "SubSystem", -1);
    model.component("comp1").physics("lms").feature("X4")
         .label("\u5b50\u7cfb\u7edf\u5b9e\u4f8b X4\uff1a\u53f3\u540e\u8f6e");
    model.component("comp1").physics("lms").feature("X4").set("subSystemName", "sub1");
    model.component("comp1").physics("lms").feature("X4").setIndex("Connections", 4, 0, 0);
    model.component("comp1").physics("lms").feature("X4").setIndex("Connections", 8, 1, 0);
    model.component("comp1").physics("lms").create("E1", "ExternalSource", -1);
    model.component("comp1").physics("lms").feature("E1").label("\u5916\u90e8\u6e90 E1\uff1a\u5de6\u524d");
    model.component("comp1").physics("lms").feature("E1").setIndex("Connections", 5, 0, 0);
    model.component("comp1").physics("lms").feature("E1").setIndex("Connections", 9, 1, 0);
    model.component("comp1").physics("lms").feature("E1").set("InputDisp", "Coupled");
    model.component("comp1").physics("lms").feature("E1").set("includeForce", false);
    model.component("comp1").physics("lms").feature("E1").set("includeDisplacement", false);
    model.component("comp1").physics("lms").create("E2", "ExternalSource", -1);
    model.component("comp1").physics("lms").feature("E2").label("\u5916\u90e8\u6e90 E2\uff1a\u53f3\u524d");
    model.component("comp1").physics("lms").feature("E2").setIndex("Connections", 6, 0, 0);
    model.component("comp1").physics("lms").feature("E2").setIndex("Connections", 10, 1, 0);
    model.component("comp1").physics("lms").feature("E2").set("InputDisp", "Coupled");
    model.component("comp1").physics("lms").feature("E2").set("includeForce", false);
    model.component("comp1").physics("lms").feature("E2").set("includeDisplacement", false);
    model.component("comp1").physics("lms").create("E3", "ExternalSource", -1);
    model.component("comp1").physics("lms").feature("E3").label("\u5916\u90e8\u6e90 E3\uff1a\u5de6\u540e");
    model.component("comp1").physics("lms").feature("E3").setIndex("Connections", 7, 0, 0);
    model.component("comp1").physics("lms").feature("E3").setIndex("Connections", 11, 1, 0);
    model.component("comp1").physics("lms").feature("E3").set("InputDisp", "Coupled");
    model.component("comp1").physics("lms").feature("E3").set("includeForce", false);
    model.component("comp1").physics("lms").feature("E3").set("includeDisplacement", false);
    model.component("comp1").physics("lms").create("E4", "ExternalSource", -1);
    model.component("comp1").physics("lms").feature("E4").label("\u5916\u90e8\u6e90 E4\uff1a\u53f3\u540e");
    model.component("comp1").physics("lms").feature("E4").setIndex("Connections", 8, 0, 0);
    model.component("comp1").physics("lms").feature("E4").setIndex("Connections", 12, 1, 0);
    model.component("comp1").physics("lms").feature("E4").set("InputDisp", "Coupled");
    model.component("comp1").physics("lms").feature("E4").set("includeForce", false);
    model.component("comp1").physics("lms").feature("E4").set("includeDisplacement", false);
    model.component("comp1").physics("lms").create("X5", "SubSystem", -1);
    model.component("comp1").physics("lms").feature("X5")
         .label("\u5b50\u7cfb\u7edf\u5b9e\u4f8b X5\uff1a\u5de6\u524d\u5ea7\u6905");
    model.component("comp1").physics("lms").feature("X5").set("subSystemName", "sub2");
    model.component("comp1").physics("lms").feature("X5").setIndex("Connections", 9, 0, 0);
    model.component("comp1").physics("lms").create("X6", "SubSystem", -1);
    model.component("comp1").physics("lms").feature("X6")
         .label("\u5b50\u7cfb\u7edf\u5b9e\u4f8b X6\uff1a\u53f3\u524d\u5ea7\u6905");
    model.component("comp1").physics("lms").feature("X6").set("subSystemName", "sub2");
    model.component("comp1").physics("lms").feature("X6").setIndex("Connections", 10, 0, 0);
    model.component("comp1").physics("lms").create("X7", "SubSystem", -1);
    model.component("comp1").physics("lms").feature("X7")
         .label("\u5b50\u7cfb\u7edf\u5b9e\u4f8b X7\uff1a\u5de6\u540e\u5ea7\u6905");
    model.component("comp1").physics("lms").feature("X7").set("subSystemName", "sub2");
    model.component("comp1").physics("lms").feature("X7").setIndex("Connections", 11, 0, 0);
    model.component("comp1").physics("lms").create("X8", "SubSystem", -1);
    model.component("comp1").physics("lms").feature("X8")
         .label("\u5b50\u7cfb\u7edf\u5b9e\u4f8b X8\uff1a\u53f3\u540e\u5ea7\u6905");
    model.component("comp1").physics("lms").feature("X8").set("subSystemName", "sub2");
    model.component("comp1").physics("lms").feature("X8").setIndex("Connections", 12, 0, 0);
    model.component("comp1").physics("mbd").create("rd1", "RigidDomain", 3);
    model.component("comp1").physics("mbd").feature("rd1").selection().set(1);
    model.component("comp1").physics("mbd").feature("rd1").set("rho_mat", "userdef");
    model.component("comp1").physics("mbd").feature("rd1").set("CenterOfRotationType", "UserDefined");
    model.component("comp1").physics("mbd").feature("rd1").create("mmi1", "MassInertia", -1);
    model.component("comp1").physics("mbd").feature("rd1").feature("mmi1").set("mt", "m_body");
    model.component("comp1").physics("mbd").feature("rd1").feature("mmi1")
         .set("mi", new String[]{"I_roll", "0", "0", "0", "I_pitch", "0", "0", "0", "0"});
    model.component("comp1").physics("mbd").feature("rd1").create("pdr1", "PrescribedDispRot", -1);
    model.component("comp1").physics("mbd").feature("rd1").feature("pdr1").setIndex("Direction", true, 0);
    model.component("comp1").physics("mbd").feature("rd1").feature("pdr1").setIndex("Direction", true, 1);
    model.component("comp1").physics("mbd").feature("rd1").feature("pdr1")
         .set("RotationType", "ConstrainedRotationGroup");
    model.component("comp1").physics("mbd").feature("rd1").feature("pdr1").setIndex("ConstrainedRotation", true, 2);

    model.component("comp1").multiphysics().create("lsc1", "LumpedStructureConnection", -1);
    model.component("comp1").multiphysics("lsc1").label("\u96c6\u603b-\u7ed3\u6784\u8fde\u63a5\uff1a\u5de6\u524d");
    model.component("comp1").multiphysics("lsc1").selection("ExtSrcSel1").set(7);
    model.component("comp1").multiphysics("lsc1").selection("ExtSrcSel2").set(8);
    model.component("comp1").multiphysics().create("lsc2", "LumpedStructureConnection", -1);
    model.component("comp1").multiphysics("lsc2").label("\u96c6\u603b-\u7ed3\u6784\u8fde\u63a5\uff1a\u53f3\u524d");
    model.component("comp1").multiphysics("lsc2").selection("ExtSrcSel1").set(5);
    model.component("comp1").multiphysics("lsc2").selection("ExtSrcSel2").set(6);
    model.component("comp1").multiphysics("lsc2").set("LmsFeat", "E2");
    model.component("comp1").multiphysics().create("lsc3", "LumpedStructureConnection", -1);
    model.component("comp1").multiphysics("lsc3").label("\u96c6\u603b-\u7ed3\u6784\u8fde\u63a5\uff1a\u5de6\u540e");
    model.component("comp1").multiphysics("lsc3").selection("ExtSrcSel1").set(3);
    model.component("comp1").multiphysics("lsc3").selection("ExtSrcSel2").set(4);
    model.component("comp1").multiphysics("lsc3").set("LmsFeat", "E3");
    model.component("comp1").multiphysics().create("lsc4", "LumpedStructureConnection", -1);
    model.component("comp1").multiphysics("lsc4").label("\u96c6\u603b-\u7ed3\u6784\u8fde\u63a5\uff1a\u53f3\u540e");
    model.component("comp1").multiphysics("lsc4").selection("ExtSrcSel1").set(1);
    model.component("comp1").multiphysics("lsc4").selection("ExtSrcSel2").set(2);
    model.component("comp1").multiphysics("lsc4").set("LmsFeat", "E4");

    model.study("std1").feature("time").set("tlist", "range(0,0.0002,2)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 15);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u4f4d\u79fb (mbd)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature("surf1").feature().create("def1", "Deform");
    model.result("pg1").feature("surf1").feature("def1").label("\u53d8\u5f62");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u901f\u5ea6 (mbd)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("vol1", "Volume");
    model.result("pg2").feature("vol1").label("\u57df\u7f16\u53f7");
    model.result("pg2").feature("vol1").set("descractive", true);
    model.result("pg2").feature("vol1").set("expr", "mod(dom,10)");
    model.result("pg2").feature("vol1").set("descr", "\u57df\u7f16\u53f7");
    model.result("pg2").feature("vol1").set("rangecoloractive", "on");
    model.result("pg2").feature("vol1").set("rangecolormin", -0.5);
    model.result("pg2").feature("vol1").set("rangecolormax", 9.5);
    model.result("pg2").feature("vol1").set("colortable", "Cyclic");
    model.result("pg2").feature("vol1").set("colorlegend", false);
    model.result("pg2").feature("vol1").set("colortabletype", "discrete");
    model.result("pg2").feature("vol1").set("smooth", "none");
    model.result("pg2").feature("vol1").set("data", "parent");
    model.result("pg2").feature("vol1").feature().create("def1", "Deform");
    model.result("pg2").feature("vol1").feature("def1").label("\u53d8\u5f62");
    model.result("pg2").feature().create("arwl1", "ArrowLine");
    model.result("pg2").feature("arwl1").label("\u7ebf\u4e0a\u7bad\u5934");
    model.result("pg2").feature("arwl1").set("expr", new String[]{"mbd.u_tX", "mbd.u_tY", "mbd.u_tZ"});
    model.result("pg2").feature("arwl1").set("placement", "elements");
    model.result("pg2").feature("arwl1").set("data", "parent");
    model.result("pg2").feature("arwl1").feature().create("def1", "Deform");
    model.result("pg2").feature("arwl1").feature("def1").label("\u53d8\u5f62");
    model.result("pg1").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u8def\u9762\u6fc0\u52b1\uff08\u5de6\u8f6e\uff09");
    model.result("pg3").set("titletype", "label");
    model.result("pg3").setIndex("looplevelinput", "interp", 0);
    model.result("pg3").setIndex("interp", "range(0,0.0002,0.1)", 0);
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").set("expr", new String[]{"lms.u_1"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"\u8282\u70b9 u_1 \u5904\u7684\u4f4d\u79fb"});
    model.result("pg3").feature("glob1").set("unit", new String[]{"m"});
    model.result("pg3").feature("glob1").set("expr", new String[]{"lms.u_1", "lms.u_3"});
    model.result("pg3").feature("glob1")
         .set("descr", new String[]{"\u8282\u70b9 u_1 \u5904\u7684\u4f4d\u79fb", "\u8282\u70b9 u_3 \u5904\u7684\u4f4d\u79fb"});
    model.result("pg3").feature("glob1").setIndex("descr", "\u5de6\u524d", 0);
    model.result("pg3").feature("glob1").setIndex("descr", "\u5de6\u540e", 1);
    model.result("pg3").run();
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u4f4d\u79fb (m)");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u8f66\u8f86\u8fd0\u52a8 (CG)");
    model.result("pg4").set("titletype", "label");
    model.result("pg4").set("twoyaxes", true);
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").set("expr", new String[]{"mbd.rd1.thx"});
    model.result("pg4").feature("glob1").set("descr", new String[]{"\u521a\u4f53\u8f6c\u52a8\uff0cx \u5206\u91cf"});
    model.result("pg4").feature("glob1").set("unit", new String[]{"rad"});
    model.result("pg4").feature("glob1").set("expr", new String[]{"mbd.rd1.thx", "mbd.rd1.thy"});
    model.result("pg4").feature("glob1")
         .set("descr", new String[]{"\u521a\u4f53\u8f6c\u52a8\uff0cx \u5206\u91cf", "\u521a\u4f53\u8f6c\u52a8\uff0cy \u5206\u91cf"});
    model.result("pg4").feature("glob1").setIndex("unit", "deg", 0);
    model.result("pg4").feature("glob1").setIndex("descr", "\u6a2a\u6447", 0);
    model.result("pg4").feature("glob1").setIndex("unit", "deg", 1);
    model.result("pg4").feature("glob1").setIndex("descr", "\u4fef\u4ef0", 1);
    model.result("pg4").run();
    model.result("pg4").create("glob2", "Global");
    model.result("pg4").feature("glob2").set("markerpos", "datapoints");
    model.result("pg4").feature("glob2").set("linewidth", "preference");
    model.result("pg4").feature("glob2").set("expr", new String[]{"mbd.rd1.w"});
    model.result("pg4").feature("glob2").set("descr", new String[]{"\u521a\u4f53\u4f4d\u79fb\uff0cz \u5206\u91cf"});
    model.result("pg4").feature("glob2").set("unit", new String[]{"m"});
    model.result("pg4").feature("glob2").setIndex("descr", "\u8d77\u4f0f", 0);
    model.result("pg4").feature("glob2").set("plotonsecyaxis", true);
    model.result("pg4").run();
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u65cb\u8f6c (deg)");
    model.result("pg4").set("yseclabelactive", true);
    model.result("pg4").set("yseclabel", "\u4f4d\u79fb (m)");
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u8f66\u8f86\u901f\u5ea6 (CG)");
    model.result("pg5").set("ylabel", "\u89d2\u901f\u5ea6 (rad/s)");
    model.result("pg5").set("yseclabel", "\u901f\u5ea6 (m/s)");
    model.result("pg5").run();
    model.result("pg5").feature("glob1").setIndex("expr", "mbd.rd1.th_tx", 0);
    model.result("pg5").feature("glob1").setIndex("expr", "mbd.rd1.th_ty", 1);
    model.result("pg5").run();
    model.result("pg5").feature("glob2").setIndex("expr", "mbd.rd1.Wt", 0);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u5ea7\u6905\u4f4d\u79fb");
    model.result("pg6").set("titletype", "label");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").set("expr", new String[]{"lms.X5.m1.u"});
    model.result("pg6").feature("glob1").set("descr", new String[]{"\u4f4d\u79fb (m1)"});
    model.result("pg6").feature("glob1").set("unit", new String[]{"m"});
    model.result("pg6").feature("glob1").set("expr", new String[]{"lms.X5.m1.u", "lms.X6.m1.u"});
    model.result("pg6").feature("glob1").set("descr", new String[]{"\u4f4d\u79fb (m1)", "\u4f4d\u79fb (m1)"});
    model.result("pg6").feature("glob1").set("expr", new String[]{"lms.X5.m1.u", "lms.X6.m1.u", "lms.X7.m1.u"});
    model.result("pg6").feature("glob1")
         .set("descr", new String[]{"\u4f4d\u79fb (m1)", "\u4f4d\u79fb (m1)", "\u4f4d\u79fb (m1)"});
    model.result("pg6").feature("glob1")
         .set("expr", new String[]{"lms.X5.m1.u", "lms.X6.m1.u", "lms.X7.m1.u", "lms.X8.m1.u"});
    model.result("pg6").feature("glob1")
         .set("descr", new String[]{"\u4f4d\u79fb (m1)", "\u4f4d\u79fb (m1)", "\u4f4d\u79fb (m1)", "\u4f4d\u79fb (m1)"});
    model.result("pg6").feature("glob1").setIndex("descr", "\u5de6\u524d", 0);
    model.result("pg6").feature("glob1").setIndex("descr", "\u53f3\u524d", 1);
    model.result("pg6").feature("glob1").setIndex("descr", "\u5de6\u540e", 2);
    model.result("pg6").feature("glob1").setIndex("descr", "\u53f3\u540e", 3);
    model.result("pg6").run();
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "\u4f4d\u79fb (m)");
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("\u5ea7\u6905\u52a0\u901f\u5ea6");
    model.result("pg7").set("ylabel", "\u52a0\u901f\u5ea6 (m/s^2)");
    model.result("pg7").run();
    model.result("pg7").feature("glob1").setIndex("expr", "d(lms.X5.m1.dudt,t)", 0);
    model.result("pg7").feature("glob1").setIndex("expr", "d(lms.X6.m1.dudt,t)", 1);
    model.result("pg7").feature("glob1").setIndex("expr", "d(lms.X7.m1.dudt,t)", 2);
    model.result("pg7").feature("glob1").setIndex("expr", "d(lms.X8.m1.dudt,t)", 3);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").label("\u529b\uff08\u5de6\u524d\u8f6e\uff09");
    model.result("pg8").set("titletype", "label");
    model.result("pg8").create("glob1", "Global");
    model.result("pg8").feature("glob1").set("markerpos", "datapoints");
    model.result("pg8").feature("glob1").set("linewidth", "preference");
    model.result("pg8").feature("glob1").set("expr", new String[]{"lms.X1.K1.f"});
    model.result("pg8").feature("glob1").set("descr", new String[]{"\u5f39\u7c27\u529b (K1)"});
    model.result("pg8").feature("glob1").set("unit", new String[]{"N"});
    model.result("pg8").feature("glob1").set("expr", new String[]{"lms.X1.K1.f", "lms.X1.K2.f"});
    model.result("pg8").feature("glob1")
         .set("descr", new String[]{"\u5f39\u7c27\u529b (K1)", "\u5f39\u7c27\u529b (K2)"});
    model.result("pg8").feature("glob1").set("expr", new String[]{"lms.X1.K1.f", "lms.X1.K2.f", "lms.X1.C1.f"});
    model.result("pg8").feature("glob1")
         .set("descr", new String[]{"\u5f39\u7c27\u529b (K1)", "\u5f39\u7c27\u529b (K2)", "\u963b\u5c3c\u529b (C1)"});
    model.result("pg8").run();
    model.result("pg8").set("ylabelactive", true);
    model.result("pg8").set("ylabel", "\u529b (N)");
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").run();
    model.result("pg9").label("\u529b\uff08\u5de6\u524d\u5ea7\u6905\uff09");
    model.result("pg9").set("titletype", "label");
    model.result("pg9").create("glob1", "Global");
    model.result("pg9").feature("glob1").set("markerpos", "datapoints");
    model.result("pg9").feature("glob1").set("linewidth", "preference");
    model.result("pg9").feature("glob1").set("expr", new String[]{"lms.X5.K1.f"});
    model.result("pg9").feature("glob1").set("descr", new String[]{"\u5f39\u7c27\u529b (K1)"});
    model.result("pg9").feature("glob1").set("unit", new String[]{"N"});
    model.result("pg9").feature("glob1").set("expr", new String[]{"lms.X5.K1.f", "lms.X5.C1.f"});
    model.result("pg9").feature("glob1")
         .set("descr", new String[]{"\u5f39\u7c27\u529b (K1)", "\u963b\u5c3c\u529b (C1)"});
    model.result("pg9").run();
    model.result("pg9").set("ylabelactive", true);
    model.result("pg9").set("ylabel", "\u529b (N)");
    model.result("pg9").run();
    model.result("pg7").run();

    model.title("\u8f66\u8f86\u60ac\u6302\u7cfb\u7edf\u96c6\u603b\u6a21\u578b");

    model
         .description("\u672c\u4f8b\u5206\u6790\u5177\u6709\u5341\u4e00\u4e2a\u81ea\u7531\u5ea6\u7684\u8f66\u8f86\u60ac\u6302\u7cfb\u7edf\u7684\u96c6\u603b\u6a21\u578b\u3002\u4f7f\u7528\u201c\u96c6\u603b\u673a\u68b0\u7cfb\u7edf\u201d\u63a5\u53e3\u7684\u201c\u8d28\u91cf\u201d\u3001\u201c\u5f39\u7c27\u201d\u548c\u201c\u963b\u5c3c\u5668\u201d\u8282\u70b9\u4e3a\u8f66\u8f6e\uff08\u5305\u62ec\u60ac\u6302\u7cfb\u7edf\uff09\u4ee5\u53ca\u5ea7\u6905\u548c\u4e58\u5ba2\u5efa\u6a21\u3002\u4f7f\u7528\u201c\u591a\u4f53\u52a8\u529b\u5b66\u201d\u63a5\u53e3\u5c06\u5177\u6709\u4e09\u4e2a\u81ea\u7531\u5ea6\u7684\u8f66\u8eab\u4f5c\u4e3a\u521a\u4f53\u8fdb\u884c\u5efa\u6a21\u3002\n\n\u5e76\u4f7f\u7528\u201c\u96c6\u603b\u673a\u68b0\u7cfb\u7edf\u201d\u63a5\u53e3\u7684\u201c\u5916\u90e8\u6e90\u201d\u8282\u70b9\u5c06\u8f66\u8eab\u7684\u591a\u4f53\u52a8\u529b\u5b66\u6a21\u578b\u4e0e\u7cfb\u7edf\u5176\u4f59\u90e8\u5206\u7684\u96c6\u603b\u6a21\u578b\u8fde\u63a5\u8d77\u6765\u3002\u901a\u8fc7\u6267\u884c\u77ac\u6001\u5206\u6790\u6765\u8ba1\u7b97\u8f66\u8f86\u8fd0\u52a8\u4ee5\u53ca\u7ed9\u5b9a\u8def\u9762\u4e0d\u5e73\u5ea6\u65f6\u5ea7\u4f4d\u7684\u632f\u52a8\u7b49\u7ea7\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("lumped_vehicle_suspension_system.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
