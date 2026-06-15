/*
 * golf_swing_mechanics.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:21 by COMSOL 6.3.0.290. */
public class golf_swing_mechanics {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Multibody_Dynamics_Module\\Biomechanics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("mbd", "MultibodyDynamics", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/mbd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Ish", "0.8[kg*m^2]", "\u80a9\u90e8\u60ef\u6027\u77e9");
    model.param().set("Ia", "0.354[kg*m^2]", "\u81c2\u90e8\u60ef\u6027\u77e9");
    model.param().set("Ig", "0.01367[kg*m^2]", "\u63e1\u628a\u60ef\u6027\u77e9");
    model.param().set("Ic", "0.02571[kg*m^2]", "\u7403\u6746\u60ef\u6027\u77e9");
    model.param().set("Ma", "8.644[kg]", "\u81c2\u90e8\u8d28\u91cf");
    model.param().set("Mg", "1.8994[kg]", "\u63e1\u628a\u8d28\u91cf");
    model.param().set("Mc", "0.2924[kg]", "\u7403\u6746\u8d28\u91cf");
    model.param().set("ka", "5000[N*m/rad]", "\u81c2\u90e8\u505c\u6b62\u521a\u5ea6");
    model.param().set("kw", "1000[N*m/rad]", "\u624b\u8155\u505c\u6b62\u521a\u5ea6");
    model.param().set("ks", "2000[N*m/rad]", "\u8f74\u5173\u8282\u521a\u5ea6");
    model.param().set("ca", "5[N*m*s/rad]", "\u81c2\u90e8\u505c\u6b62\u963b\u5c3c\u7cfb\u6570");
    model.param().set("cw", "5[N*m*s/rad]", "\u624b\u8155\u505c\u6b62\u963b\u5c3c\u7cfb\u6570");
    model.param().set("cs", "5[N*m*s/rad]", "\u8f74\u5173\u8282\u963b\u5c3c\u7cfb\u6570");
    model.param().set("Tsh_max", "272.64[N*m]", "\u6700\u5927\u80a9\u90e8\u626d\u77e9");
    model.param().set("Ta_max", "209.34[N*m]", "\u6700\u5927\u81c2\u90e8\u626d\u77e9");
    model.param().set("Tw_max", "34.67[N*m]", "\u6700\u5927\u624b\u8155\u626d\u77e9");
    model.param().set("Ta_rate", "3326[N*m/s]", "\u81c2\u90e8\u626d\u77e9\u7684\u53d8\u5316\u7387");
    model.param().set("xb", "0.15[m]", "\u7403\u4f4d\u7f6e\uff0cx \u5750\u6807");
    model.param().set("tw", "0.1[s]", "\u624b\u8155\u626d\u77e9\u5207\u6362\u65f6\u95f4");
    model.param().set("lambda", "100[1/s]", "\u5207\u6362\u901f\u7387\u53c2\u6570");

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "golf_swing_mechanics.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("createpairs", false);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().set(14);

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("stop", "(intop1(x)>xb)*(intop1(y)<0)", "\u505c\u6b62\u51c6\u5219");
    model.component("comp1").variable("var1")
         .set("Tsh", "Tsh_max*tanh(lambda*t)", "\u80a9\u90e8\u4f5c\u7528\u626d\u77e9");
    model.component("comp1").variable("var1")
         .set("Ta", "min(Ta_max,Ta_rate*t)", "\u81c2\u90e8\u4f5c\u7528\u626d\u77e9");
    model.component("comp1").variable("var1")
         .set("Tw", "Tw_max*tanh(lambda*(t-tw))", "\u624b\u8155\u4f5c\u7528\u626d\u77e9");

    model.component("comp1").physics("mbd").create("rd1", "RigidDomain", 2);
    model.component("comp1").physics("mbd").feature("rd1").label("\u521a\u6027\u6750\u6599\uff1a\u80a9\u90e8");
    model.component("comp1").physics("mbd").feature("rd1").selection().set(4);
    model.component("comp1").physics("mbd").feature("rd1").set("rho_mat", "userdef");
    model.component("comp1").physics("mbd").feature("rd1").create("mmi1", "MassInertia", -1);
    model.component("comp1").physics("mbd").feature("rd1").feature("mmi1").set("Iz", "Ish");
    model.component("comp1").physics("mbd").create("rd2", "RigidDomain", 2);
    model.component("comp1").physics("mbd").feature("rd2").label("\u521a\u6027\u6750\u6599\uff1a\u624b\u81c2");
    model.component("comp1").physics("mbd").feature("rd2").selection().set(2);
    model.component("comp1").physics("mbd").feature("rd2").set("rho_mat", "userdef");
    model.component("comp1").physics("mbd").feature("rd2").create("mmi1", "MassInertia", -1);
    model.component("comp1").physics("mbd").feature("rd2").feature("mmi1")
         .set("CenterOfMassType", "CentroidOfSelectedEntities");
    model.component("comp1").physics("mbd").feature("rd2").feature("mmi1").set("EntityLevel", "Point");
    model.component("comp1").physics("mbd").feature("rd2").feature("mmi1").set("mt", "Ma");
    model.component("comp1").physics("mbd").feature("rd2").feature("mmi1").set("Iz", "Ia");
    model.component("comp1").physics("mbd").feature("rd2").feature("mmi1").feature("cmp1").selection().set(8);
    model.component("comp1").physics("mbd").create("rd3", "RigidDomain", 2);
    model.component("comp1").physics("mbd").feature("rd3").label("\u521a\u6027\u6750\u6599\uff1a\u63e1\u628a");
    model.component("comp1").physics("mbd").feature("rd3").selection().set(1);
    model.component("comp1").physics("mbd").feature("rd3").set("rho_mat", "userdef");
    model.component("comp1").physics("mbd").feature("rd3").create("mmi1", "MassInertia", -1);
    model.component("comp1").physics("mbd").feature("rd3").feature("mmi1")
         .set("CenterOfMassType", "CentroidOfSelectedEntities");
    model.component("comp1").physics("mbd").feature("rd3").feature("mmi1").set("EntityLevel", "Point");
    model.component("comp1").physics("mbd").feature("rd3").feature("mmi1").set("mt", "Mg");
    model.component("comp1").physics("mbd").feature("rd3").feature("mmi1").set("Iz", "Ig");
    model.component("comp1").physics("mbd").feature("rd3").feature("mmi1").feature("cmp1").selection().set(3);
    model.component("comp1").physics("mbd").create("rd4", "RigidDomain", 2);
    model.component("comp1").physics("mbd").feature("rd4").label("\u521a\u6027\u6750\u6599\uff1a\u7403\u6746");
    model.component("comp1").physics("mbd").feature("rd4").selection().set(3);
    model.component("comp1").physics("mbd").feature("rd4").set("rho_mat", "userdef");
    model.component("comp1").physics("mbd").feature("rd4").create("mmi1", "MassInertia", -1);
    model.component("comp1").physics("mbd").feature("rd4").feature("mmi1")
         .set("CenterOfMassType", "CentroidOfSelectedEntities");
    model.component("comp1").physics("mbd").feature("rd4").feature("mmi1").set("EntityLevel", "Point");
    model.component("comp1").physics("mbd").feature("rd4").feature("mmi1").set("mt", "Mc");
    model.component("comp1").physics("mbd").feature("rd4").feature("mmi1").set("Iz", "Ic");
    model.component("comp1").physics("mbd").feature("rd4").feature("mmi1").feature("cmp1").selection().set(13);

    model.nodeGroup().create("grp1", "Physics", "mbd");
    model.nodeGroup("grp1").placeAfter("init1");
    model.nodeGroup("grp1").add("rd1");
    model.nodeGroup("grp1").add("rd2");
    model.nodeGroup("grp1").add("rd3");
    model.nodeGroup("grp1").add("rd4");
    model.nodeGroup("grp1").label("\u521a\u6027\u6750\u6599");

    model.component("comp1").physics("mbd").create("hgj1", "HingeJoint", -1);
    model.component("comp1").physics("mbd").feature("hgj1").label("\u94f0\u94fe\u5173\u8282\uff1a\u80a9\u90e8");
    model.component("comp1").physics("mbd").feature("hgj1").set("Source", "fixed");
    model.component("comp1").physics("mbd").feature("hgj1").set("Destination", "rd1");
    model.component("comp1").physics("mbd").feature("hgj1").feature("cjb1").selection().set(13);
    model.component("comp1").physics("mbd").feature("hgj1").create("afm1", "AppliedForceAndMoment", -1);
    model.component("comp1").physics("mbd").feature("hgj1").feature("afm1")
         .set("AppliedOnSelectedAttachment", "Joint");
    model.component("comp1").physics("mbd").feature("hgj1").feature("afm1").set("Ms", "Tsh");
    model.component("comp1").physics("mbd").create("hgj2", "HingeJoint", -1);
    model.component("comp1").physics("mbd").feature("hgj2").label("\u94f0\u94fe\u5173\u8282\uff1a\u624b\u81c2");
    model.component("comp1").physics("mbd").feature("hgj2").set("Source", "rd1");
    model.component("comp1").physics("mbd").feature("hgj2").set("Destination", "rd2");
    model.component("comp1").physics("mbd").feature("hgj2").feature("cjb1").selection().set(8);
    model.component("comp1").physics("mbd").feature("hgj2").create("sd1", "SpringAndDamper", -1);
    model.component("comp1").physics("mbd").feature("hgj2").feature("sd1").set("k_th", "ka*(mbd.hgj2.th<0)");
    model.component("comp1").physics("mbd").feature("hgj2").feature("sd1").set("c_th", "ca*(mbd.hgj2.th<0)");
    model.component("comp1").physics("mbd").feature("hgj2").create("afm1", "AppliedForceAndMoment", -1);
    model.component("comp1").physics("mbd").feature("hgj2").feature("afm1")
         .set("AppliedOnSelectedAttachment", "Joint");
    model.component("comp1").physics("mbd").feature("hgj2").feature("afm1").set("Ms", "Ta");
    model.component("comp1").physics("mbd").create("hgj3", "HingeJoint", -1);
    model.component("comp1").physics("mbd").feature("hgj3").label("\u94f0\u94fe\u5173\u8282\uff1a\u624b\u8155");
    model.component("comp1").physics("mbd").feature("hgj3").set("Source", "rd2");
    model.component("comp1").physics("mbd").feature("hgj3").set("Destination", "rd3");
    model.component("comp1").physics("mbd").feature("hgj3").feature("cjb1").selection().set(1);
    model.component("comp1").physics("mbd").feature("hgj3").create("sd1", "SpringAndDamper", -1);
    model.component("comp1").physics("mbd").feature("hgj3").feature("sd1").set("k_th", "kw*(mbd.hgj3.th<0)");
    model.component("comp1").physics("mbd").feature("hgj3").feature("sd1").set("c_th", "cw*(mbd.hgj3.th<0)");
    model.component("comp1").physics("mbd").feature("hgj3").create("afm1", "AppliedForceAndMoment", -1);
    model.component("comp1").physics("mbd").feature("hgj3").feature("afm1")
         .set("AppliedOnSelectedAttachment", "Joint");
    model.component("comp1").physics("mbd").feature("hgj3").feature("afm1").set("Ms", "Tw");
    model.component("comp1").physics("mbd").create("hgj4", "HingeJoint", -1);
    model.component("comp1").physics("mbd").feature("hgj4").label("\u94f0\u94fe\u5173\u8282\uff1a\u8f74");
    model.component("comp1").physics("mbd").feature("hgj4").set("Source", "rd3");
    model.component("comp1").physics("mbd").feature("hgj4").set("Destination", "rd4");
    model.component("comp1").physics("mbd").feature("hgj4").feature("cjb1").selection().set(9);
    model.component("comp1").physics("mbd").feature("hgj4").create("sd1", "SpringAndDamper", -1);
    model.component("comp1").physics("mbd").feature("hgj4").feature("sd1").set("k_th", "ks");
    model.component("comp1").physics("mbd").feature("hgj4").feature("sd1").set("c_th", "cs");

    model.nodeGroup().create("grp2", "Physics", "mbd");
    model.nodeGroup("grp2").placeAfter("init1");
    model.nodeGroup("grp2").add("hgj1");
    model.nodeGroup("grp2").add("hgj2");
    model.nodeGroup("grp2").add("hgj3");
    model.nodeGroup("grp2").add("hgj4");
    model.nodeGroup("grp2").label("\u94f0\u94fe\u5173\u8282");

    model.component("comp1").variable("var1").set("Ta_eff", "Ta-mbd.hgj2.sd1.Ms-mbd.hgj2.sd1.Md");
    model.component("comp1").variable("var1").descr("Ta_eff", "\u6709\u6548\u624b\u81c2\u626d\u77e9");
    model.component("comp1").variable("var1").set("Tw_eff", "Tw-mbd.hgj3.sd1.Ms-mbd.hgj3.sd1.Md");
    model.component("comp1").variable("var1").descr("Tw_eff", "\u6709\u6548\u624b\u8155\u626d\u77e9");

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "Ish", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "kg*m^2", 0);
    model.study("std1").feature("param").setIndex("pname", "Ish", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "kg*m^2", 0);
    model.study("std1").feature("param").setIndex("pname", "tw", 0);
    model.study("std1").feature("param").setIndex("plistarr", "0.15 0.19 0.23", 0);
    model.study("std1").feature("time").set("tlist", "range(0,2e-3,0.26)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").create("st1", "StopCondition");
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "comp1.stop", 0);
    model.sol("sol1").feature("t1").feature("st1").set("storestopcondsol", "stepafter");

    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u4f4d\u79fb (mbd)");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 126, 0);
    model.result("pg1").setIndex("looplevel", 3, 1);
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature("surf1").feature().create("def1", "Deform");
    model.result("pg1").feature("surf1").feature("def1").label("\u53d8\u5f62");
    model.result("pg1").feature("surf1").feature("def1").set("scaleactive", true);
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u901f\u5ea6 (mbd)");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 126, 0);
    model.result("pg2").setIndex("looplevel", 3, 1);
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u57df\u7f16\u53f7");
    model.result("pg2").feature("surf1").set("descractive", true);
    model.result("pg2").feature("surf1").set("expr", "mod(dom,10)");
    model.result("pg2").feature("surf1").set("descr", "\u57df\u7f16\u53f7");
    model.result("pg2").feature("surf1").set("rangecoloractive", "on");
    model.result("pg2").feature("surf1").set("rangecolormin", -0.5);
    model.result("pg2").feature("surf1").set("rangecolormax", 9.5);
    model.result("pg2").feature("surf1").set("colortable", "Cyclic");
    model.result("pg2").feature("surf1").set("colorlegend", false);
    model.result("pg2").feature("surf1").set("colortabletype", "discrete");
    model.result("pg2").feature("surf1").set("smooth", "none");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature("surf1").feature().create("def1", "Deform");
    model.result("pg2").feature("surf1").feature("def1").label("\u53d8\u5f62");
    model.result("pg2").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg2").feature().create("arwl1", "ArrowLine");
    model.result("pg2").feature("arwl1").label("\u7ebf\u4e0a\u7bad\u5934");
    model.result("pg2").feature("arwl1").set("expr", new String[]{"mbd.u_tX", "mbd.u_tY"});
    model.result("pg2").feature("arwl1").set("placement", "elements");
    model.result("pg2").feature("arwl1").set("data", "parent");
    model.result("pg2").feature("arwl1").feature().create("def1", "Deform");
    model.result("pg2").feature("arwl1").feature("def1").label("\u53d8\u5f62");
    model.result("pg2").feature("arwl1").feature("def1").set("scaleactive", true);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("pttraj1", "PointTrajectories");
    model.result("pg1").feature("pttraj1").selection().set(1, 9, 14);
    model.result("pg1").feature("pttraj1").set("linetype", "tube");
    model.result("pg1").feature("pttraj1").create("col1", "Color");
    model.result("pg1").run();
    model.result("pg1").feature("pttraj1").feature("col1").set("expr", "t");
    model.result("pg1").feature("pttraj1").feature("col1").set("colorlegend", false);
    model.result("pg1").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u6746\u5934\u901f\u5ea6");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").set("titletype", "none");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u6746\u5934\u901f\u5ea6 (m/s)");
    model.result("pg3").set("legendpos", "upperleft");
    model.result("pg3").create("ptgr1", "PointGraph");
    model.result("pg3").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg3").feature("ptgr1").set("linewidth", "preference");
    model.result("pg3").feature("ptgr1").selection().set(14);
    model.result("pg3").feature("ptgr1").set("expr", "mbd.vel");
    model.result("pg3").feature("ptgr1").set("descr", "\u901f\u5ea6\u5927\u5c0f");
    model.result("pg3").feature("ptgr1").set("linewidth", 2);
    model.result("pg3").feature("ptgr1").set("linemarker", "cycle");
    model.result("pg3").feature("ptgr1").set("markerpos", "interp");
    model.result("pg3").feature("ptgr1").set("legend", true);
    model.result("pg3").feature("ptgr1").set("legendmethod", "evaluated");
    model.result("pg3").feature("ptgr1").set("legendpattern", "tw=eval(tw,s) s");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u9a71\u52a8\u626d\u77e9");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevelinput", "manual", 1);
    model.result("pg4").setIndex("looplevel", new int[]{2}, 1);
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u9a71\u52a8\u626d\u77e9 (N-m)");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").set("expr", new String[]{"Tsh"});
    model.result("pg4").feature("glob1").set("descr", new String[]{"\u80a9\u90e8\u4f5c\u7528\u626d\u77e9"});
    model.result("pg4").feature("glob1").set("unit", new String[]{"N*m"});
    model.result("pg4").feature("glob1").set("expr", new String[]{"Tsh", "Ta"});
    model.result("pg4").feature("glob1")
         .set("descr", new String[]{"\u80a9\u90e8\u4f5c\u7528\u626d\u77e9", "\u81c2\u90e8\u4f5c\u7528\u626d\u77e9"});
    model.result("pg4").feature("glob1").set("expr", new String[]{"Tsh", "Ta", "Tw"});
    model.result("pg4").feature("glob1")
         .set("descr", new String[]{"\u80a9\u90e8\u4f5c\u7528\u626d\u77e9", "\u81c2\u90e8\u4f5c\u7528\u626d\u77e9", "\u624b\u8155\u4f5c\u7528\u626d\u77e9"});
    model.result("pg4").feature("glob1").set("expr", new String[]{"Tsh", "Ta", "Tw", "Ta_eff"});
    model.result("pg4").feature("glob1")
         .set("descr", new String[]{"\u80a9\u90e8\u4f5c\u7528\u626d\u77e9", "\u81c2\u90e8\u4f5c\u7528\u626d\u77e9", "\u624b\u8155\u4f5c\u7528\u626d\u77e9", "\u6709\u6548\u624b\u81c2\u626d\u77e9"});
    model.result("pg4").feature("glob1").set("expr", new String[]{"Tsh", "Ta", "Tw", "Ta_eff", "Tw_eff"});
    model.result("pg4").feature("glob1")
         .set("descr", new String[]{"\u80a9\u90e8\u4f5c\u7528\u626d\u77e9", "\u81c2\u90e8\u4f5c\u7528\u626d\u77e9", "\u624b\u8155\u4f5c\u7528\u626d\u77e9", "\u6709\u6548\u624b\u81c2\u626d\u77e9", "\u6709\u6548\u624b\u8155\u626d\u77e9"});
    model.result("pg4").feature("glob1").set("linewidth", 2);
    model.result("pg4").feature("glob1").set("linemarker", "cycle");
    model.result("pg4").feature("glob1").set("markerpos", "interp");
    model.result("pg4").run();
    model.result("pg4").set("axislimits", true);
    model.result("pg4").set("ymin", -70);
    model.result("pg4").set("ymax", 500);
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u76f8\u5bf9\u65cb\u8f6c");
    model.result("pg5").set("ylabel", "\u76f8\u5bf9\u65cb\u8f6c (deg)");
    model.result("pg5").set("legendpos", "upperleft");
    model.result("pg5").run();
    model.result("pg5").feature("glob1").set("expr", new String[]{"mbd.hgj2.th"});
    model.result("pg5").feature("glob1").set("descr", new String[]{"\u76f8\u5bf9\u65cb\u8f6c"});
    model.result("pg5").feature("glob1").set("unit", new String[]{"rad"});
    model.result("pg5").feature("glob1").setIndex("unit", "deg", 0);
    model.result("pg5").feature("glob1").setIndex("descr", "\u81c2\u5173\u8282", 0);
    model.result("pg5").feature("glob1").set("expr", new String[]{"mbd.hgj2.th", "mbd.hgj3.th"});
    model.result("pg5").feature("glob1")
         .set("descr", new String[]{"\u81c2\u5173\u8282", "\u76f8\u5bf9\u65cb\u8f6c"});
    model.result("pg5").feature("glob1").setIndex("unit", "deg", 1);
    model.result("pg5").feature("glob1").setIndex("descr", "\u8155\u5173\u8282", 1);
    model.result("pg5").feature("glob1").set("expr", new String[]{"mbd.hgj2.th", "mbd.hgj3.th", "mbd.hgj4.th"});
    model.result("pg5").feature("glob1")
         .set("descr", new String[]{"\u81c2\u5173\u8282", "\u8155\u5173\u8282", "\u76f8\u5bf9\u65cb\u8f6c"});
    model.result("pg5").feature("glob1").setIndex("unit", "deg", 2);
    model.result("pg5").feature("glob1").setIndex("descr", "\u8f74\u5173\u8282", 2);
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u80a9\u90e8-\u624b\u81c2-\u7403\u6746\u8fd0\u52a8");
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr1").set("linewidth", "preference");
    model.result("pg6").feature("lngr1").set("data", "dset2");
    model.result("pg6").feature("lngr1").setIndex("looplevelinput", "manual", 1);
    model.result("pg6").feature("lngr1").setIndex("looplevel", new int[]{1}, 1);
    model.result("pg6").feature("lngr1").setIndex("looplevelinput", "interp", 0);
    model.result("pg6").feature("lngr1").setIndex("interp", "range(0,2e-2,0.26)", 0);
    model.result("pg6").feature("lngr1").selection().set(2, 6, 10, 14);
    model.result("pg6").feature("lngr1").set("expr", "y");
    model.result("pg6").feature("lngr1").set("xdata", "expr");
    model.result("pg6").feature("lngr1").set("xdataexpr", "x");
    model.result("pg6").feature("lngr1").set("linecolor", "black");
    model.result("pg6").feature("lngr1").set("linewidth", 2);
    model.result("pg6").run();
    model.result("pg6").create("ptgr1", "PointGraph");
    model.result("pg6").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg6").feature("ptgr1").set("linewidth", "preference");
    model.result("pg6").feature("ptgr1").set("data", "dset2");
    model.result("pg6").feature("ptgr1").setIndex("looplevelinput", "manual", 1);
    model.result("pg6").feature("ptgr1").setIndex("looplevel", new int[]{1}, 1);
    model.result("pg6").feature("ptgr1").setIndex("looplevelinput", "interp", 0);
    model.result("pg6").feature("ptgr1").setIndex("interp", "range(0,2e-2,0.26)", 0);
    model.result("pg6").feature("ptgr1").selection().set(6, 14, 18);
    model.result("pg6").feature("ptgr1").set("expr", "y");
    model.result("pg6").feature("ptgr1").set("xdata", "expr");
    model.result("pg6").feature("ptgr1").set("xdataexpr", "x");
    model.result("pg6").feature("ptgr1").set("linecolor", "red");
    model.result("pg6").feature("ptgr1").set("linestyle", "none");
    model.result("pg6").feature("ptgr1").set("linemarker", "point");
    model.result("pg6").run();
    model.result("pg6").feature().duplicate("lngr2", "lngr1");
    model.result("pg6").run();
    model.result("pg6").feature("lngr2").setIndex("looplevel", new int[]{2}, 1);
    model.result("pg6").feature("lngr2").set("xdataexpr", "x+2.5");
    model.result("pg6").run();
    model.result("pg6").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg6").run();
    model.result("pg6").feature("ptgr2").setIndex("looplevel", new int[]{2}, 1);
    model.result("pg6").feature("ptgr2").set("xdataexpr", "x+2.5");
    model.result("pg6").run();
    model.result("pg6").feature().duplicate("lngr3", "lngr2");
    model.result("pg6").run();
    model.result("pg6").feature("lngr3").setIndex("looplevel", new int[]{3}, 1);
    model.result("pg6").feature("lngr3").set("xdataexpr", "x+5");
    model.result("pg6").run();
    model.result("pg6").feature().duplicate("ptgr3", "ptgr2");
    model.result("pg6").run();
    model.result("pg6").feature("ptgr3").setIndex("looplevel", new int[]{3}, 1);
    model.result("pg6").feature("ptgr3").set("xdataexpr", "x+5");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").set("titletype", "none");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "x \u5750\u6807 (m)");
    model.result("pg6").set("preserveaspect", true);
    model.result("pg6").run();

    model.title("\u9ad8\u5c14\u592b\u6325\u6746\u4e2d\u7684\u529b\u5b66");

    model
         .description("\u6325\u52a8\u9ad8\u5c14\u592b\u7403\u6746\u7684\u65b9\u5f0f\u4f1a\u5f71\u54cd\u51fb\u7403\u7684\u8f93\u51fa\u529b\u5ea6\u3002\u672c\u4f8b\u5bf9\u9ad8\u5c14\u592b\u6325\u6746\u8fdb\u884c\u591a\u4f53\u52a8\u529b\u5b66\u5206\u6790\uff0c\u4ece\u800c\u5728\u51fb\u7403\u65f6\u8fbe\u5230\u6746\u5934\u901f\u5ea6\u6700\u5feb\u8fd9\u4e2a\u76ee\u7684\u3002\u8155\u5173\u8282\u7684\u626d\u77e9\u5728\u63d0\u9ad8\u51fb\u7403\u529b\u5ea6\u8fc7\u7a0b\u4e2d\u8d77\u7740\u91cd\u8981\u7684\u4f5c\u7528\uff0c\u901a\u8fc7\u53ef\u53d8\u53c2\u6570\u53ef\u89c2\u5bdf\u5230\u5b83\u5bf9\u6746\u5934\u901f\u5ea6\u7684\u5f71\u54cd\u3002");

    model.label("golf_swing_mechanics.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
