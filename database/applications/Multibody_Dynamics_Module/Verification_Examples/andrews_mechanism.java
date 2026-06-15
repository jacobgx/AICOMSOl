/*
 * andrews_mechanism.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:27 by COMSOL 6.3.0.290. */
public class andrews_mechanism {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Multibody_Dynamics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("mbd", "MultibodyDynamics", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/mbd", true);

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "andrews_mechanism.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").run("fin");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("xC", "0.014[m]", "\u70b9 C \u7684 x \u5750\u6807");
    model.param().set("yC", "0.072[m]", "\u70b9 C \u7684 y \u5750\u6807");
    model.param().set("beta0", "-0.0620[rad]", "\u521d\u59cb beta");
    model.param().set("gamma0", "0.4552[rad]", "\u521d\u59cb gamma");
    model.param().set("delta0", "0.4873[rad]", "\u521d\u59cb delta");
    model.param().set("epsilon0", "1.2305[rad]", "\u521d\u59cb epsilon");
    model.param().set("theta0", "0[rad]", "\u521d\u59cb theta");
    model.param().set("phi0", "0.2227[rad]", "\u521d\u59cb phi");
    model.param().set("omega0", "0.2227[rad]", "\u521d\u59cb omega");
    model.param().set("c0", "4530[N/m]", "\u5f39\u7c27\u7cfb\u6570");
    model.param().set("l0", "0.07785[m]", "\u5f39\u7c27\u672a\u62c9\u4f38\u7684\u957f\u5ea6");
    model.param().set("M0", "0.033[N*m]", "\u4f5c\u7528\u529b\u77e9");
    model.param().set("m1", "0.04325[kg]", "\u8fde\u6746 1 \u7684\u8d28\u91cf");
    model.param().set("m2", "0.00365[kg]", "\u8fde\u6746 2 \u7684\u8d28\u91cf");
    model.param().set("m3", "0.02373[kg]", "\u8fde\u6746 3 \u7684\u8d28\u91cf");
    model.param().set("m4", "0.00706[kg]", "\u8fde\u6746 4 \u7684\u8d28\u91cf");
    model.param().set("m5", "0.07050[kg]", "\u8fde\u6746 5 \u7684\u8d28\u91cf");
    model.param().set("m6", "0.00706[kg]", "\u8fde\u6746 6 \u7684\u8d28\u91cf");
    model.param().set("m7", "0.05498[kg]", "\u8fde\u6746 7 \u7684\u8d28\u91cf");
    model.param().set("I1", "2.194e-6[kg*m^2]", "\u8fde\u6746 1 \u7684\u8d28\u91cf\u60ef\u6027\u77e9");
    model.param().set("I2", "4.410e-7[kg*m^2]", "\u8fde\u6746 2 \u7684\u8d28\u91cf\u60ef\u6027\u77e9");
    model.param().set("I3", "5.255e-6[kg*m^2]", "\u8fde\u6746 3 \u7684\u8d28\u91cf\u60ef\u6027\u77e9");
    model.param().set("I4", "5.667e-7[kg*m^2]", "\u8fde\u6746 4 \u7684\u8d28\u91cf\u60ef\u6027\u77e9");
    model.param().set("I5", "1.169e-5[kg*m^2]", "\u8fde\u6746 5 \u7684\u8d28\u91cf\u60ef\u6027\u77e9");
    model.param().set("I6", "5.667e-7[kg*m^2]", "\u8fde\u6746 6 \u7684\u8d28\u91cf\u60ef\u6027\u77e9");
    model.param().set("I7", "1.912e-5[kg*m^2]", "\u8fde\u6746 7 \u7684\u8d28\u91cf\u60ef\u6027\u77e9");

    model.component("comp1").physics("mbd").create("rd1", "RigidDomain", 2);
    model.component("comp1").physics("mbd").feature("rd1").selection().set(7);
    model.component("comp1").physics("mbd").feature("rd1").set("rho_mat", "userdef");
    model.component("comp1").physics("mbd").feature("rd1").create("mmi1", "MassInertia", -1);
    model.component("comp1").physics("mbd").feature("rd1").feature("mmi1")
         .set("CenterOfMassType", "CentroidOfSelectedEntities");
    model.component("comp1").physics("mbd").feature("rd1").feature("mmi1").set("EntityLevel", "Point");
    model.component("comp1").physics("mbd").feature("rd1").feature("mmi1").set("mt", "m1");
    model.component("comp1").physics("mbd").feature("rd1").feature("mmi1").set("Iz", "I1");
    model.component("comp1").physics("mbd").feature("rd1").feature("mmi1").feature("cmp1").selection().set(44);
    model.component("comp1").physics("mbd").feature().duplicate("rd2", "rd1");
    model.component("comp1").physics("mbd").feature("rd2").selection().set(6);
    model.component("comp1").physics("mbd").feature("rd2").feature("mmi1").set("mt", "m2");
    model.component("comp1").physics("mbd").feature("rd2").feature("mmi1").set("Iz", "I2");
    model.component("comp1").physics("mbd").feature("rd2").feature("mmi1").feature("cmp1").selection().set(39);
    model.component("comp1").physics("mbd").feature().duplicate("rd3", "rd2");
    model.component("comp1").physics("mbd").feature("rd3").selection().set(3);
    model.component("comp1").physics("mbd").feature("rd3").feature("mmi1").set("mt", "m3");
    model.component("comp1").physics("mbd").feature("rd3").feature("mmi1").set("Iz", "I3");
    model.component("comp1").physics("mbd").feature("rd3").feature("mmi1").feature("cmp1").selection().set(23);
    model.component("comp1").physics("mbd").feature().duplicate("rd4", "rd3");
    model.component("comp1").physics("mbd").feature("rd4").selection().set(4);
    model.component("comp1").physics("mbd").feature("rd4").feature("mmi1").set("mt", "m4");
    model.component("comp1").physics("mbd").feature("rd4").feature("mmi1").set("Iz", "I4");
    model.component("comp1").physics("mbd").feature("rd4").feature("mmi1").feature("cmp1").selection().set(29);
    model.component("comp1").physics("mbd").feature().duplicate("rd5", "rd4");
    model.component("comp1").physics("mbd").feature("rd5").selection().set(1);
    model.component("comp1").physics("mbd").feature("rd5").feature("mmi1").set("mt", "m5");
    model.component("comp1").physics("mbd").feature("rd5").feature("mmi1").set("Iz", "I5");
    model.component("comp1").physics("mbd").feature("rd5").feature("mmi1").feature("cmp1").selection().set(4);
    model.component("comp1").physics("mbd").feature().duplicate("rd6", "rd5");
    model.component("comp1").physics("mbd").feature("rd6").selection().set(5);
    model.component("comp1").physics("mbd").feature("rd6").feature("mmi1").set("mt", "m6");
    model.component("comp1").physics("mbd").feature("rd6").feature("mmi1").set("Iz", "I6");
    model.component("comp1").physics("mbd").feature("rd6").feature("mmi1").feature("cmp1").selection().set(34);
    model.component("comp1").physics("mbd").feature().duplicate("rd7", "rd6");
    model.component("comp1").physics("mbd").feature("rd7").selection().set(2);
    model.component("comp1").physics("mbd").feature("rd7").feature("mmi1").set("mt", "m7");
    model.component("comp1").physics("mbd").feature("rd7").feature("mmi1").set("Iz", "I7");
    model.component("comp1").physics("mbd").feature("rd7").feature("mmi1").feature("cmp1").selection().set(13);
    model.component("comp1").physics("mbd").feature("rd1").create("am1", "AppliedMoment", -1);
    model.component("comp1").physics("mbd").feature("rd1").feature("am1").set("Mtz", "M0");

    model.nodeGroup().create("grp1", "Physics", "mbd");
    model.nodeGroup("grp1").placeAfter("init1");
    model.nodeGroup("grp1").add("rd1");
    model.nodeGroup("grp1").add("rd2");
    model.nodeGroup("grp1").add("rd3");
    model.nodeGroup("grp1").add("rd4");
    model.nodeGroup("grp1").add("rd5");
    model.nodeGroup("grp1").add("rd6");
    model.nodeGroup("grp1").add("rd7");
    model.nodeGroup("grp1").label("\u521a\u6027\u6750\u6599");

    model.component("comp1").physics("mbd").create("spd1", "SpringDamper", -1);
    model.component("comp1").physics("mbd").feature("spd1").set("Source", "fixed");
    model.component("comp1").physics("mbd").feature("spd1").set("xs0", new String[]{"xC", "yC", "0"});
    model.component("comp1").physics("mbd").feature("spd1").set("Destination", "rd3");
    model.component("comp1").physics("mbd").feature("spd1").set("DestinationPoint", "CentroidOfSelectedEntities");
    model.component("comp1").physics("mbd").feature("spd1").feature("dpb1").selection().set(25);
    model.component("comp1").physics("mbd").feature("spd1").set("k", "c0");
    model.component("comp1").physics("mbd").feature("spd1").set("FreeLengthType", "specifyFreeLength");
    model.component("comp1").physics("mbd").feature("spd1").set("lf", "l0");
    model.component("comp1").physics("mbd").create("hgj1", "HingeJoint", -1);
    model.component("comp1").physics("mbd").feature("hgj1").set("Source", "fixed");
    model.component("comp1").physics("mbd").feature("hgj1").set("Destination", "rd1");
    model.component("comp1").physics("mbd").feature("hgj1").set("EntityLevel", "Point");
    model.component("comp1").physics("mbd").feature("hgj1").feature("cjp1").selection().set(42, 43);
    model.component("comp1").physics("mbd").feature().duplicate("hgj2", "hgj1");
    model.component("comp1").physics("mbd").feature("hgj2").set("Source", "rd1");
    model.component("comp1").physics("mbd").feature("hgj2").set("Destination", "rd2");
    model.component("comp1").physics("mbd").feature("hgj2").feature("cjp1").selection().set(45, 46);
    model.component("comp1").physics("mbd").feature().duplicate("hgj3", "hgj2");
    model.component("comp1").physics("mbd").feature("hgj3").set("Source", "rd2");
    model.component("comp1").physics("mbd").feature("hgj3").set("Destination", "rd3");
    model.component("comp1").physics("mbd").feature("hgj3").feature("cjp1").selection().set(37, 38);
    model.component("comp1").physics("mbd").feature().duplicate("hgj4", "hgj3");
    model.component("comp1").physics("mbd").feature("hgj4").set("Destination", "rd4");
    model.component("comp1").physics("mbd").feature().duplicate("hgj5", "hgj4");
    model.component("comp1").physics("mbd").feature("hgj5").set("Destination", "rd6");
    model.component("comp1").physics("mbd").feature().duplicate("hgj6", "hgj2");
    model.component("comp1").physics("mbd").feature("hgj6").set("Source", "rd5");
    model.component("comp1").physics("mbd").feature("hgj6").set("Destination", "rd4");
    model.component("comp1").physics("mbd").feature("hgj6").feature("cjp1").selection().set(27, 28);
    model.component("comp1").physics("mbd").feature().duplicate("hgj7", "hgj6");
    model.component("comp1").physics("mbd").feature("hgj7").set("Source", "rd7");
    model.component("comp1").physics("mbd").feature("hgj7").set("Destination", "rd6");
    model.component("comp1").physics("mbd").feature("hgj7").feature("cjp1").selection().set(32, 33);
    model.component("comp1").physics("mbd").feature().duplicate("hgj8", "hgj5");
    model.component("comp1").physics("mbd").feature("hgj8").set("Source", "fixed");
    model.component("comp1").physics("mbd").feature("hgj8").set("Destination", "rd5");
    model.component("comp1").physics("mbd").feature("hgj8").feature("cjp1").selection().set(1, 2);
    model.component("comp1").physics("mbd").feature().duplicate("hgj9", "hgj8");
    model.component("comp1").physics("mbd").feature("hgj9").set("Destination", "rd7");
    model.component("comp1").physics("mbd").feature("hgj9").feature("cjp1").selection().set(9, 10);
    model.component("comp1").physics("mbd").feature().duplicate("hgj10", "hgj9");
    model.component("comp1").physics("mbd").feature("hgj10").set("Destination", "rd3");
    model.component("comp1").physics("mbd").feature("hgj10").feature("cjp1").selection().set(17, 18);

    model.nodeGroup().create("grp2", "Physics", "mbd");
    model.nodeGroup("grp2").placeAfter("spd1");
    model.nodeGroup("grp2").add("hgj1");
    model.nodeGroup("grp2").add("hgj2");
    model.nodeGroup("grp2").add("hgj3");
    model.nodeGroup("grp2").add("hgj4");
    model.nodeGroup("grp2").add("hgj5");
    model.nodeGroup("grp2").add("hgj6");
    model.nodeGroup("grp2").add("hgj7");
    model.nodeGroup("grp2").add("hgj8");
    model.nodeGroup("grp2").add("hgj9");
    model.nodeGroup("grp2").add("hgj10");
    model.nodeGroup("grp2").label("\u94f0\u94fe\u5173\u8282");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("beta", "mod(mbd.hgj1.th-pi,2*pi)-pi+beta0");
    model.component("comp1").variable("var1").descr("beta", "beta");
    model.component("comp1").variable("var1").set("theta", "mod(mbd.hgj2.th-pi,2*pi)-pi+theta0");
    model.component("comp1").variable("var1").descr("theta", "theta");
    model.component("comp1").variable("var1").set("gamma", "mbd.hgj10.th+gamma0");
    model.component("comp1").variable("var1").descr("gamma", "gamma");
    model.component("comp1").variable("var1").set("delta", "mbd.hgj8.th+delta0");
    model.component("comp1").variable("var1").descr("delta", "delta");
    model.component("comp1").variable("var1").set("phi", "mbd.hgj6.th+phi0");
    model.component("comp1").variable("var1").descr("phi", "phi");
    model.component("comp1").variable("var1").set("omega", "mbd.hgj7.th-omega0");
    model.component("comp1").variable("var1").descr("omega", "omega");

    model.study("std1").feature("time").set("tlist", "range(0,1e-4,0.03)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u4f4d\u79fb (mbd)");
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
    model.result("pg1").set("frametype", "material");
    model.result("pg1").setIndex("looplevel", 281, 0);
    model.result("pg1").run();
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").label("beta");
    model.result().table("tbl1").importData("andrews_mechanism_beta.txt");
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").label("theta");
    model.result().table("tbl2").importData("andrews_mechanism_theta.txt");
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").label("gamma");
    model.result().table("tbl3").importData("andrews_mechanism_gamma.txt");
    model.result().table().create("tbl4", "Table");
    model.result().table("tbl4").label("delta");
    model.result().table("tbl4").importData("andrews_mechanism_delta.txt");
    model.result().table().create("tbl5", "Table");
    model.result().table("tbl5").label("phi");
    model.result().table("tbl5").importData("andrews_mechanism_phi.txt");
    model.result().table().create("tbl6", "Table");
    model.result().table("tbl6").label("omega");
    model.result().table("tbl6").importData("andrews_mechanism_omega.txt");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u89d2\u5ea6");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").set("expr", new String[]{"beta"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"beta"});
    model.result("pg3").feature("glob1").set("unit", new String[]{"rad"});
    model.result("pg3").feature("glob1").set("expr", new String[]{"beta", "theta"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"beta", "theta"});
    model.result("pg3").feature("glob1").set("expr", new String[]{"beta", "theta", "gamma"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"beta", "theta", "gamma"});
    model.result("pg3").feature("glob1").set("expr", new String[]{"beta", "theta", "gamma", "delta"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"beta", "theta", "gamma", "delta"});
    model.result("pg3").feature("glob1").set("expr", new String[]{"beta", "theta", "gamma", "delta", "phi"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"beta", "theta", "gamma", "delta", "phi"});
    model.result("pg3").feature("glob1")
         .set("expr", new String[]{"beta", "theta", "gamma", "delta", "phi", "omega"});
    model.result("pg3").feature("glob1")
         .set("descr", new String[]{"beta", "theta", "gamma", "delta", "phi", "omega"});
    model.result("pg3").feature("glob1").set("linewidth", 2);
    model.result("pg3").feature("glob1").set("legend", false);
    model.result("pg3").run();
    model.result("pg3").create("tblp1", "Table");
    model.result("pg3").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg3").feature("tblp1").set("linewidth", "preference");
    model.result("pg3").feature("tblp1").set("linemarker", "cycle");
    model.result("pg3").feature("tblp1").set("linestyle", "none");
    model.result("pg3").feature("tblp1").set("legend", true);
    model.result("pg3").feature("tblp1").set("legendmethod", "manual");
    model.result("pg3").feature("tblp1").setIndex("legends", "\\beta", 0);
    model.result("pg3").feature().duplicate("tblp2", "tblp1");
    model.result("pg3").run();
    model.result("pg3").feature("tblp2").set("table", "tbl2");
    model.result("pg3").feature("tblp2").setIndex("legends", "\\theta", 0);
    model.result("pg3").feature().duplicate("tblp3", "tblp2");
    model.result("pg3").run();
    model.result("pg3").feature("tblp3").set("table", "tbl3");
    model.result("pg3").feature("tblp3").setIndex("legends", "\\gamma", 0);
    model.result("pg3").feature().duplicate("tblp4", "tblp3");
    model.result("pg3").run();
    model.result("pg3").feature("tblp4").set("table", "tbl4");
    model.result("pg3").feature("tblp4").setIndex("legends", "\\delta", 0);
    model.result("pg3").feature().duplicate("tblp5", "tblp4");
    model.result("pg3").run();
    model.result("pg3").feature("tblp5").set("table", "tbl5");
    model.result("pg3").feature("tblp5").setIndex("legends", "\\phi", 0);
    model.result("pg3").feature().duplicate("tblp6", "tblp5");
    model.result("pg3").run();
    model.result("pg3").feature("tblp6").set("table", "tbl6");
    model.result("pg3").feature("tblp6").setIndex("legends", "\\omega", 0);
    model.result("pg3").run();
    model.result("pg3").set("legendpos", "upperleft");
    model.result("pg3").set("titletype", "none");
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "\u65f6\u95f4 (s)");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u89d2\u5ea6 (rad)");
    model.result("pg3").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("fontsize", "9");
    model.result().export("anim1").set("colortheme", "globaltheme");
    model.result().export("anim1").set("customcolor", new double[]{1, 1, 1});
    model.result().export("anim1").set("background", "color");
    model.result().export("anim1").set("gltfincludelines", "on");
    model.result().export("anim1").set("title1d", "on");
    model.result().export("anim1").set("legend1d", "on");
    model.result().export("anim1").set("logo1d", "on");
    model.result().export("anim1").set("options1d", "on");
    model.result().export("anim1").set("title2d", "on");
    model.result().export("anim1").set("legend2d", "on");
    model.result().export("anim1").set("logo2d", "on");
    model.result().export("anim1").set("options2d", "off");
    model.result().export("anim1").set("title3d", "on");
    model.result().export("anim1").set("legend3d", "on");
    model.result().export("anim1").set("logo3d", "on");
    model.result().export("anim1").set("options3d", "off");
    model.result().export("anim1").set("axisorientation", "on");
    model.result().export("anim1").set("grid", "on");
    model.result().export("anim1").set("axes1d", "on");
    model.result().export("anim1").set("axes2d", "on");
    model.result().export("anim1").set("showgrid", "on");
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("maxframes", 100);
    model.result("pg1").run();

    model.title("Andrew \u6324\u538b\u673a\u6784");

    model
         .description("\u672c\u4f8b\u9610\u91ca\u4e86 Andrew \u6324\u538b\u673a\u6784\u5efa\u6a21\uff0c\u8be5\u673a\u6784\u4e5f\u79f0\u4e3a\u4e03\u8fde\u6746\u673a\u6784\uff0c\u662f\u591a\u4f53\u52a8\u529b\u5b66\u7684\u57fa\u51c6\u95ee\u9898\u3002\u5176\u4e2d\u7684\u8fde\u63a5\u4ef6\u4f5c\u4e3a\u521a\u4f53\u5904\u7406\uff0c\u91c7\u7528 10 \u4e2a\u94f0\u94fe\u5173\u8282\u8fdb\u884c\u8fde\u63a5\u3002\u7ed3\u679c\u5305\u542b\u4e0d\u540c\u7684\u89d2\u5ea6\uff0c\u5e76\u4e0e\u53c2\u8003\u6587\u732e\u4e2d\u7684\u7ed3\u679c\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.label("andrews_mechanism.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
