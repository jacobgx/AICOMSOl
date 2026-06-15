/*
 * turbocharger_transient_analysis.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:46 by COMSOL 6.3.0.290. */
public class turbocharger_transient_analysis {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Rotordynamics_Module\\Automotive_and_Aerospace");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("rotbm", "BeamRotor", "geom1");
    model.component("comp1").physics().create("hdb", "HydrodynamicBearing", "geom1");

    model.component("comp1").multiphysics().create("brbc1", "BeamRotorBearingCoupling", 2);
    model.component("comp1").multiphysics("brbc1").set("BeamRotor_physics", "rotbm");
    model.component("comp1").multiphysics("brbc1").set("Bearing_physics", "hdb");
    model.component("comp1").multiphysics("brbc1").selection().all();

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/rotbm", true);
    model.study("std1").feature("time").setSolveFor("/physics/hdb", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/brbc1", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Ow", "8000[rpm]", "\u8f6c\u5b50\u89d2\u901f\u5ea6");
    model.param().set("Er", "205[GPa]", "\u8f6c\u5b50\u7684\u6768\u6c0f\u6a21\u91cf");
    model.param().set("nur", "0.3", "\u8f6c\u5b50\u7684\u6cca\u677e\u6bd4");
    model.param().set("rhor", "7800", "\u8f6c\u5b50\u5bc6\u5ea6");
    model.param().set("L", "0.15[m]", "\u8f6c\u5b50\u957f\u5ea6");
    model.param().set("x1", "0[m]", "\u8f74\u7684\u5de6\u7aef\u5750\u6807");
    model.param().set("x2", "0.1*L", "\u6da1\u8f6e\u7684\u5750\u6807");
    model.param().set("x3", "0.3*L", "\u5de6\u8f74\u627f\u7684\u5750\u6807");
    model.param().set("x4", "0.7*L", "\u53f3\u8f74\u627f\u7684\u5750\u6807");
    model.param().set("x5", "0.9*L", "\u538b\u7f29\u673a\u7684\u5750\u6807");
    model.param().set("x6", "L", "\u8f6c\u5b50\u7684\u53f3\u7aef\u5750\u6807");
    model.param().set("m_t", "1.4[kg]", "\u6da1\u8f6e\u8d28\u91cf");
    model.param().set("Id_t", "6.3e-4[kg*m^2]", "\u6da1\u8f6e\u7684\u76f4\u5f84\u60ef\u6027\u77e9");
    model.param().set("Ip_t", "1.26e-5[kg*m^2]", "\u6da1\u8f6e\u7684\u6781\u60ef\u6027\u77e9");
    model.param().set("m_c", "1.0[kg]", "\u538b\u7f29\u673a\u8d28\u91cf");
    model.param().set("Id_c", "4.5e-4[kg*m^2]", "\u538b\u7f29\u673a\u7684\u76f4\u5f84\u60ef\u6027\u77e9");
    model.param().set("Ip_c", "9e-4[kg*m^2]", "\u538b\u7f29\u673a\u7684\u6781\u60ef\u6027\u77e9");
    model.param().create("par2");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("m_r", "0.02[kg]", "\u73af\u8d28\u91cf");
    model.param("par2").set("R_o", "9e-3[m]", "\u73af\u5916\u534a\u5f84");
    model.param("par2").set("R_i", "6e-3[m]", "\u73af\u5185\u534a\u5f84");
    model.param("par2").set("Ip_r", "0.5*m_r*(R_o^2+R_i^2)", "\u73af\u7684\u6781\u60ef\u6027\u77e9");
    model.param("par2").set("Id_r", "0.5*Ip_r+m_r*Lb^2/12", "\u73af\u7684\u76f4\u5f84\u60ef\u6027\u77e9");
    model.param("par2").set("C_o", "8e-5[m]", "\u5916\u819c\u95f4\u9699");
    model.param("par2").set("C_i", "2e-5[m]", "\u5185\u819c\u95f4\u9699");
    model.param("par2").set("mu0", "0.06[Pa*s]", "\u6da6\u6ed1\u6cb9\u9ecf\u5ea6");
    model.param("par2").set("Lb", "0.01[m]", "\u8f74\u627f\u957f\u5ea6");

    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol1").set("x", "x1 x2 x3 L/2");
    model.component("comp1").geom("geom1").feature("pol1").set("y", 0);
    model.component("comp1").geom("geom1").feature("pol1").set("z", 0);
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u8f6c\u5b50");
    model.component("comp1").geom("geom1").feature("pol1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("type", "surface");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "R_o");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "Lb");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"x3-Lb/2", "0", "0"});
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "x");
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("\u8f74\u627f\u9762");
    model.component("comp1").geom("geom1").feature("cyl1").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").feature().duplicate("cyl2", "cyl1");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", "R_i");
    model.component("comp1").geom("geom1").feature().duplicate("cyl3", "cyl1");
    model.component("comp1").geom("geom1").feature("cyl3").set("h", "0.3*Lb");
    model.component("comp1").geom("geom1").feature("cyl3").set("pos", new String[]{"x3-0.15*Lb", "0", "0"});
    model.component("comp1").geom("geom1").feature().duplicate("cyl4", "cyl3");
    model.component("comp1").geom("geom1").feature("cyl4").set("r", "R_i");
    model.component("comp1").geom("geom1").run("cyl4");
    model.component("comp1").geom("geom1").create("cyl5", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl5").set("r", "0.1*R_o");
    model.component("comp1").geom("geom1").feature("cyl5").set("h", "2.5*(R_o-R_i)");
    model.component("comp1").geom("geom1").feature("cyl5").set("pos", new String[]{"x3", "0", "0.5*R_i"});
    model.component("comp1").geom("geom1").feature("cyl5").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").run("cyl5");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("cyl5");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", "range(0,60,300)");
    model.component("comp1").geom("geom1").feature("rot1").set("axistype", "x");
    model.component("comp1").geom("geom1").selection().create("csel3", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel3").label("\u8f85\u52a9\u6c14\u7f38");
    model.component("comp1").geom("geom1").feature("rot1").set("contributeto", "csel3");
    model.component("comp1").geom("geom1").run("rot1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").named("csel2");
    model.component("comp1").geom("geom1").feature("uni1").set("keep", true);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del1").selection("input").named("csel3");
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input")
         .set("cyl1", "cyl2", "cyl3", "cyl4", "del1", "pol1");
    model.component("comp1").geom("geom1").feature("mir1").set("pos", new String[]{"L/2", "0", "0"});
    model.component("comp1").geom("geom1").feature("mir1").set("axis", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").runPre("mir1");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(4);
    model.component("comp1").selection("sel1").set("groupcontang", true);
    model.component("comp1").selection("sel1").label("\u5185\u819c\uff0c\u8f74\u627f 1");
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u5916\u819c\uff0c\u8f74\u627f 1");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(2);
    model.component("comp1").selection("sel2").set("groupcontang", true);
    model.component("comp1").selection().duplicate("sel3", "sel1");
    model.component("comp1").selection("sel3").label("\u5185\u819c\uff0c\u8f74\u627f 2");
    model.component("comp1").selection("sel3")
         .remove(3, 4, 6, 7, 11, 12, 14, 15, 19, 20, 23, 24, 25, 26, 29, 30, 35, 36, 38, 39);
    model.component("comp1").selection("sel3")
         .add(43, 44, 46, 47, 51, 52, 54, 55, 59, 60, 63, 64, 65, 66, 69, 70, 75, 76, 78, 79);
    model.component("comp1").selection().duplicate("sel4", "sel2");
    model.component("comp1").selection("sel4").label("\u5916\u819c\uff0c\u8f74\u627f 2");
    model.component("comp1").selection("sel4")
         .remove(1, 2, 5, 8, 9, 10, 13, 16, 17, 18, 21, 22, 27, 28, 31, 32, 33, 34, 37, 40);
    model.component("comp1").selection("sel4")
         .add(41, 42, 45, 48, 49, 50, 53, 56, 57, 58, 61, 62, 67, 68, 71, 72, 73, 74, 77, 80);
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").set("entitydim", 2);
    model.component("comp1").selection("uni1").set("input", new String[]{"sel1", "sel2"});
    model.component("comp1").selection("uni1").label("\u8f74\u627f 1");
    model.component("comp1").selection().duplicate("uni2", "uni1");
    model.component("comp1").selection("uni2").label("\u8f74\u627f 2");
    model.component("comp1").selection("uni2").set("input", new String[]{"sel3", "sel4"});
    model.component("comp1").selection().create("uni3", "Union");
    model.component("comp1").selection("uni3").set("entitydim", 2);
    model.component("comp1").selection("uni3").set("input", new String[]{"sel1", "sel3"});
    model.component("comp1").selection("uni3").label("\u5185\u819c");
    model.component("comp1").selection().duplicate("uni4", "uni3");
    model.component("comp1").selection("uni4").label("\u5916\u819c");
    model.component("comp1").selection("uni4").set("input", new String[]{"sel2", "sel4"});
    model.component("comp1").selection().create("cyl1", "Cylinder");
    model.component("comp1").selection("cyl1").set("entitydim", 2);
    model.component("comp1").selection("cyl1").set("axistype", "x");
    model.component("comp1").selection("cyl1").set("r", "0.5*(R_i+R_o)");
    model.component("comp1").selection("cyl1").set("rin", "0.5*R_i");
    model.component("comp1").selection("cyl1").set("top", "0.11*R_o");
    model.component("comp1").selection("cyl1").set("bottom", "-0.11*R_o");
    model.component("comp1").selection("cyl1").set("pos", new String[]{"x3", "0", "0"});
    model.component("comp1").selection("cyl1").set("condition", "inside");
    model.component("comp1").selection("cyl1").label("\u901a\u9053\u3001\u5185\u819c\u3001\u8f74\u627f 1");
    model.component("comp1").selection().duplicate("cyl2", "cyl1");
    model.component("comp1").selection("cyl2").set("r", "1.1*R_o");
    model.component("comp1").selection("cyl2").set("rin", "0.5*(R_i+R_o)");
    model.component("comp1").selection("cyl2").label("\u901a\u9053\u3001\u5916\u819c\u3001\u8f74\u627f 1");
    model.component("comp1").selection().duplicate("cyl3", "cyl1");
    model.component("comp1").selection().duplicate("cyl4", "cyl2");
    model.component("comp1").selection("cyl3").set("pos", new String[]{"x4", "0", "0"});
    model.component("comp1").selection("cyl3").label("\u901a\u9053\u3001\u5185\u819c\u3001\u8f74\u627f 2");
    model.component("comp1").selection("cyl4").label("\u901a\u9053\u3001\u5916\u819c\u3001\u8f74\u627f 2");
    model.component("comp1").selection("cyl4").set("pos", new String[]{"x4", "0", "0"});

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").selection().geom("geom1", 1);
    model.component("comp1").material("mat1").selection().named("geom1_csel1_edg");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"Er"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"nur"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rhor"});

    model.component("comp1").physics("rotbm").selection().named("geom1_csel1_edg");
    model.component("comp1").physics("rotbm").prop("RotorProperties").set("freqr", "Ow");
    model.component("comp1").physics("rotbm").prop("Results").set("createUndefGeom", false);
    model.component("comp1").physics("rotbm").feature("rcs1").set("do_circ", "2*R_i");
    model.component("comp1").physics("rotbm").create("disk1", "Disk", 0);
    model.component("comp1").physics("rotbm").feature("disk1").label("\u5706\u76d8\uff1a\u6da1\u8f6e\u673a");
    model.component("comp1").physics("rotbm").feature("disk1").set("COM", "Relative");
    model.component("comp1").physics("rotbm").feature("disk1").set("zr", "1e-4*R_i");
    model.component("comp1").physics("rotbm").feature("disk1").set("mass", "m_t");
    model.component("comp1").physics("rotbm").feature("disk1").set("Ip", "Ip_t");
    model.component("comp1").physics("rotbm").feature("disk1").set("Id", "Id_t");
    model.component("comp1").physics("rotbm").feature("disk1").selection().set(2);
    model.component("comp1").physics("rotbm").feature().duplicate("disk2", "disk1");
    model.component("comp1").physics("rotbm").feature("disk2").label("\u5706\u76d8\uff1a\u538b\u7f29\u673a");
    model.component("comp1").physics("rotbm").feature("disk2").selection().set(166);
    model.component("comp1").physics("rotbm").feature("disk2").set("mass", "m_c");
    model.component("comp1").physics("rotbm").feature("disk2").set("Ip", "Ip_c");
    model.component("comp1").physics("rotbm").feature("disk2").set("Id", "Id_c");
    model.component("comp1").physics("rotbm").create("gr1", "Gravity", 1);
    model.component("comp1").physics("hdb").create("frb1", "FloatingRingBearing", 2);
    model.component("comp1").physics("hdb").feature("frb1").selection().named("uni1");
    model.component("comp1").physics("hdb").feature("frb1").set("mr", "m_r");
    model.component("comp1").physics("hdb").feature("frb1")
         .set("Ir", new String[]{"Ip_r", "0", "0", "0", "Id_r", "0", "0", "0", "Id_r"});
    model.component("comp1").physics("hdb").feature("frb1").set("BearingCenterType", "fromGeom");
    model.component("comp1").physics("hdb").feature("frb1").set("mure_mat", "userdef");
    model.component("comp1").physics("hdb").feature("frb1").set("mure", "mu0");
    model.component("comp1").physics("hdb").feature("frb1").set("rho_mat", "userdef");
    model.component("comp1").physics("hdb").feature("frb1").feature("if1").selection().named("sel1");
    model.component("comp1").physics("hdb").feature("frb1").feature("if1").set("C", "C_i");
    model.component("comp1").physics("hdb").feature("frb1").feature("of1").selection().named("sel2");
    model.component("comp1").physics("hdb").feature("frb1").feature("of1").set("C", "C_o");
    model.component("comp1").physics("hdb").feature("frb1").create("fc1", "InnerOuterFilmConnection", -1);
    model.component("comp1").physics("hdb").feature("frb1").feature("fc1").selection("ChannelInner").named("cyl1");
    model.component("comp1").physics("hdb").feature("frb1").feature("fc1").selection("ChannelOuter").named("cyl2");
    model.component("comp1").physics("hdb").feature().duplicate("frb2", "frb1");
    model.component("comp1").physics("hdb").feature("frb2").selection().named("uni2");
    model.component("comp1").physics("hdb").feature("frb2").feature("if1").selection().named("sel3");
    model.component("comp1").physics("hdb").feature("frb2").feature("of1").selection().named("sel4");
    model.component("comp1").physics("hdb").feature("frb2").feature("fc1").selection("ChannelInner").named("cyl3");
    model.component("comp1").physics("hdb").feature("frb2").feature("fc1").selection("ChannelOuter").named("cyl4");

    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").geom(1);
    model.component("comp1").selection("sel5").label("\u8f74\u627f\u5916\u7f18");
    model.component("comp1").selection("sel5")
         .set(3, 4, 6, 7, 9, 11, 13, 15, 108, 109, 110, 111, 112, 113, 114, 115, 117, 118, 120, 121, 123, 125, 127, 129, 222, 223, 224, 225, 226, 227, 228, 229);

    model.component("comp1").multiphysics("brbc1").selection().named("sel1");
    model.component("comp1").multiphysics().duplicate("brbc2", "brbc1");
    model.component("comp1").multiphysics("brbc2").selection().named("sel3");

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().named("geom1_csel1_edg");
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").selection().set(1, 230);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("numelem", 10);
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis2").selection().set(2, 75, 116, 189);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis2").set("numelem", 20);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 73, 74, 75, 76, 77, 78, 79, 80);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection()
         .set(3, 4, 6, 7, 9, 11, 13, 15, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 120, 121, 123, 125, 127, 129, 222, 223, 224, 225, 226, 227, 228, 229);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 15);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection()
         .set(14, 16, 103, 105, 131, 132, 220, 221);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 2);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 3);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().remaining();
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,5e-4,0.1)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("timemethod", "bdf");
    model.sol("sol1").feature("t1").set("tstepsbdf", "intermediate");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-5");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 201, 0);
    model.result("pg1").create("line1", "Line");
    model.result("pg1").feature("line1").set("expr", new String[]{"rotbm.mises"});
    model.result("pg1").feature("line1").set("threshold", "manual");
    model.result("pg1").feature("line1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("line1").set("colortable", "Rainbow");
    model.result("pg1").feature("line1").set("colortabletrans", "none");
    model.result("pg1").feature("line1").set("colorscalemode", "linear");
    model.result("pg1").label("\u5e94\u529b (rotbm)");
    model.result("pg1").feature("line1").set("colortable", "Rainbow");
    model.result("pg1").feature("line1").set("linetype", "tube");
    model.result("pg1").feature("line1").set("radiusexpr", "rotbm.re");
    model.result("pg1").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg1").feature("line1").set("tuberadiusscale", 1);
    model.result("pg1").feature("line1").set("tubeendcaps", false);
    model.result("pg1").feature("line1").create("def", "Deform");
    model.result("pg1").feature("line1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("line1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u6d41\u4f53\u538b\u529b (hdb)");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "pfilm");
    model.result("pg2").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature().create("con1", "Contour");
    model.result("pg2").feature("con1").set("expr", "pfilm");
    model.result("pg2").feature("con1").set("levelrounding", false);
    model.result("pg2").feature("con1").set("colorlegend", false);
    model.result("pg2").feature("con1").set("smooth", "internal");
    model.result("pg2").feature("con1").set("inherittubescale", false);
    model.result("pg2").feature("con1").set("data", "parent");
    model.result("pg2").feature("con1").set("inheritplot", "surf1");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").feature().copy("line1", "pg1/line1");
    model.result("pg2").run();
    model.result("pg2").feature("line1").set("colortable", "JupiterAuroraBorealis");
    model.result("pg2").feature("line1").set("tuberadiusscale", 0.4);
    model.result("pg2").run();
    model.result("pg2").feature("line1").feature("def").set("scaleactive", true);
    model.result("pg2").feature("line1").feature("def").set("scale", 50);
    model.result().setOnlyPlotWhenRequested(true);
    model.result("pg2").feature("surf1").create("def1", "Deform");
    model.result("pg2").feature("surf1").feature("def1").set("expr", new String[]{"hdb.uRax", "hdb.uRay", "w"});
    model.result("pg2").feature("surf1").feature("def1").setIndex("expr", "hdb.uRaz", 2);
    model.result("pg2").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg2").feature("con1").feature().copy("def1", "pg2/surf1/def1");
    model.result("pg2").set("legendpos", "rightdouble");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").setIndex("expr", "hdb.frb1.Omegar", 0);
    model.result("pg3").feature("glob1").setIndex("unit", "rad/s", 0);
    model.result("pg3").feature("glob1").setIndex("descr", "\u73af\u901f\u5ea6\uff0c\u8f74\u627f 1", 0);
    model.result("pg3").feature("glob1").setIndex("expr", "hdb.frb2.Omegar", 1);
    model.result("pg3").feature("glob1").setIndex("unit", "rad/s", 1);
    model.result("pg3").feature("glob1").setIndex("descr", "\u73af\u901f\u5ea6\uff0c\u8f74\u627f 2", 1);
    model.result("pg3").run();
    model.result("pg3").label("\u73af\u901f\u5ea6");
    model.result("pg3").set("legendpos", "upperleft");
    model.result("pg3").set("titletype", "label");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").label("\u73af\u626d\u77e9");
    model.result("pg4").set("titletype", "label");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u626d\u77e9 (N*m)");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").set("expr", new String[]{"hdb.frb2.Mr_inx"});
    model.result("pg4").feature("glob1")
         .set("descr", new String[]{"\u73af\u4e0a\u6d41\u4f53\u529b\u77e9\uff0c\u5185\u819c\uff0cx \u5206\u91cf"});
    model.result("pg4").feature("glob1").set("unit", new String[]{"N*m"});
    model.result("pg4").feature("glob1").set("expr", new String[]{"hdb.frb2.Mr_inx", "hdb.frb2.Mr_outx"});
    model.result("pg4").feature("glob1")
         .set("descr", new String[]{"\u73af\u4e0a\u6d41\u4f53\u529b\u77e9\uff0c\u5185\u819c\uff0cx \u5206\u91cf", "\u73af\u4e0a\u6d41\u4f53\u529b\u77e9\uff0c\u5916\u819c\uff0cx \u5206\u91cf"});
    model.result("pg4").feature("glob1").setIndex("expr", "hdb.frb2.Mrx", 2);
    model.result("pg4").feature("glob1").setIndex("unit", "N*m", 2);
    model.result("pg4").feature("glob1").setIndex("descr", "\u73af\u4e0a\u7684\u529b\u77e9\uff0cx \u5206\u91cf", 2);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").label("\u73af\u5f62\u8f6c\u5b50\u8f68\u9053");
    model.result("pg5").set("titletype", "label");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "\u6309\u6bd4\u4f8b\u7f29\u5c0f\u7684 y \u4f4d\u79fb");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u6309\u6bd4\u4f8b\u7f29\u5c0f\u7684 z \u4f4d\u79fb");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").set("expr", new String[]{"hdb.frb1.uJRz_rel"});
    model.result("pg5").feature("glob1")
         .set("descr", new String[]{"\u8f74\u9888\u76f8\u5bf9\u4e8e\u73af\u7684\u4f4d\u79fb\uff08\u5df2\u7f29\u653e\uff09\uff0cz \u5206\u91cf"});
    model.result("pg5").feature("glob1").set("unit", new String[]{"1"});
    model.result("pg5").feature("glob1").set("xdata", "expr");
    model.result("pg5").feature("glob1").set("xdataexpr", "hdb.frb1.uJRy_rel");
    model.result("pg5").feature("glob1")
         .set("xdatadescr", "\u8f74\u9888\u76f8\u5bf9\u4e8e\u73af\u7684\u4f4d\u79fb\uff08\u5df2\u7f29\u653e\uff09\uff0cy \u5206\u91cf");
    model.result("pg5").feature("glob1").set("legendmethod", "manual");
    model.result("pg5").feature("glob1").setIndex("legends", "\u8f74\u627f 1", 0);
    model.result("pg5").feature().duplicate("glob2", "glob1");
    model.result("pg5").feature("glob2").set("expr", new String[]{"hdb.frb2.uJRz_rel"});
    model.result("pg5").feature("glob2")
         .set("descr", new String[]{"\u8f74\u9888\u76f8\u5bf9\u4e8e\u73af\u7684\u4f4d\u79fb\uff08\u5df2\u7f29\u653e\uff09\uff0cz \u5206\u91cf"});
    model.result("pg5").feature("glob2").set("unit", new String[]{"1"});
    model.result("pg5").feature("glob2").set("xdataexpr", "hdb.frb2.uJRy_rel");
    model.result("pg5").feature("glob2")
         .set("xdatadescr", "\u8f74\u9888\u76f8\u5bf9\u4e8e\u73af\u7684\u4f4d\u79fb\uff08\u5df2\u7f29\u653e\uff09\uff0cy \u5206\u91cf");
    model.result("pg5").feature("glob2").setIndex("legends", "\u8f74\u627f 2", 0);
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").label("\u73af\u5f62\u8f68\u9053");
    model.result("pg6").set("titletype", "label");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "\u6309\u6bd4\u4f8b\u7f29\u5c0f\u7684 y \u4f4d\u79fb");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "\u6309\u6bd4\u4f8b\u7f29\u5c0f\u7684 z \u4f4d\u79fb");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").set("expr", new String[]{"hdb.frb1.uRBz_rel"});
    model.result("pg6").feature("glob1")
         .set("descr", new String[]{"\u73af\u76f8\u5bf9\u4e8e\u8f74\u627f\u7684\u4f4d\u79fb\uff08\u5df2\u7f29\u653e\uff09\uff0cz \u5206\u91cf"});
    model.result("pg6").feature("glob1").set("unit", new String[]{"1"});
    model.result("pg6").feature("glob1").set("xdataexpr", "hdb.frb1.uRBy_rel");
    model.result("pg6").feature("glob1")
         .set("xdatadescr", "\u73af\u76f8\u5bf9\u4e8e\u8f74\u627f\u7684\u4f4d\u79fb\uff08\u5df2\u7f29\u653e\uff09\uff0cy \u5206\u91cf");
    model.result("pg6").feature("glob1").set("xdatadescractive", true);
    model.result("pg6").feature("glob1").set("legendmethod", "manual");
    model.result("pg6").feature("glob1").setIndex("legends", "\u8f74\u627f 1", 0);
    model.result("pg6").feature().duplicate("glob2", "glob1");
    model.result("pg6").feature("glob2").set("expr", new String[]{"hdb.frb2.uRBz_rel"});
    model.result("pg6").feature("glob2")
         .set("descr", new String[]{"\u73af\u76f8\u5bf9\u4e8e\u8f74\u627f\u7684\u4f4d\u79fb\uff08\u5df2\u7f29\u653e\uff09\uff0cz \u5206\u91cf"});
    model.result("pg6").feature("glob2").set("unit", new String[]{"1"});
    model.result("pg6").feature("glob2").set("xdataexpr", "hdb.frb2.uRBy_rel");
    model.result("pg6").feature("glob2").setIndex("legends", "\u8f74\u627f 2", 0);
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").set("titletype", "manual");
    model.result("pg7").set("title", "\u901a\u8fc7\u901a\u9053 1 \u7684\u6d41\u7387");
    model.result("pg7").label("\u901a\u9053\u4e2d\u7684\u6d41\u7387");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "\u901a\u9053\u4e2d\u7684\u6d41\u7387 (kg/s)");
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("markerpos", "datapoints");
    model.result("pg7").feature("glob1").set("linewidth", "preference");
    model.result("pg7").feature("glob1").setIndex("expr", "hdb.frb1.Qi1", 0);
    model.result("pg7").feature("glob1").setIndex("unit", "kg/s", 0);
    model.result("pg7").feature("glob1").setIndex("descr", "\u8f74\u627f 1", 0);
    model.result("pg7").feature("glob1").setIndex("expr", "hdb.frb2.Qi1", 1);
    model.result("pg7").feature("glob1").setIndex("unit", "kg/s", 1);
    model.result("pg7").feature("glob1").setIndex("descr", "\u8f74\u627f 2", 1);
    model.result("pg7").feature("glob1").set("linewidth", 3);
    model.result("pg7").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("fontsize", "9");
    model.result().export("anim1").set("colortheme", "globaltheme");
    model.result().export("anim1").set("customcolor", new double[]{1, 1, 1});
    model.result().export("anim1").set("background", "color");
    model.result().export("anim1").set("gltfincludelines", "on");

    return model;
  }

  public static Model run2(Model model) {
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
    model.result().export("anim1").set("plotgroup", "pg2");
    model.result().export("anim1").set("maxframes", 100);
    model.result().export("anim1").run();
    model.result("pg2").run();

    model.title("\u6d6e\u73af\u8f74\u627f\u652f\u6491\u7684\u6da1\u8f6e\u589e\u538b\u5668");

    model
         .description("\u672c\u6a21\u578b\u7814\u7a76\u7531\u4e24\u4e2a\u6d6e\u73af\u8f74\u627f\u652f\u6491\u7684\u6da1\u8f6e\u589e\u538b\u5668\u3002\u73af\u4e0a\u7684\u6cb9\u819c\u529b\u4f7f\u5176\u5728\u8f74\u627f\u5185\u6cbf\u8f74\u5411\u65cb\u8f6c\u3002\u5728\u7a33\u6001\u4e0b\uff0c\u73af\u4ee5\u6052\u5b9a\u7684\u89d2\u901f\u5ea6\u65cb\u8f6c\uff0c\u5e76\u4e14\u73af\u4e0a\u7531\u5185\u819c\u548c\u5916\u819c\u4ea7\u751f\u7684\u626d\u77e9\u8fbe\u5230\u5e73\u8861\u3002\n\n\u672c\u4f8b\u4f7f\u7528\u201c\u6881\u8f6c\u5b50\u201d\u63a5\u53e3\u4e3a\u6da1\u8f6e\u589e\u538b\u5668\u8f6c\u5b50\u5efa\u6a21\uff0c\u5e76\u901a\u8fc7\u591a\u7269\u7406\u573a\u8026\u5408\u7279\u5f81\u4f7f\u8be5\u8f6c\u5b50\u4e0e\u6d6e\u73af\u8f74\u627f\u8fdb\u884c\u8026\u5408\u3002\u6d6e\u73af\u8f74\u627f\u7684\u5185\u819c\u548c\u5916\u819c\u901a\u8fc7\u73af\u4e2d\u7684\u516d\u4e2a\u901a\u9053\u8fde\u63a5\u3002\u7ed3\u679c\u5305\u542b\u8f6c\u5b50\u7684\u5e94\u529b\u3001\u8f74\u627f\u7684\u538b\u529b\u5206\u5e03\u3001\u73af\u7684\u901f\u5ea6\u53d8\u5316\u3001\u73af\u4e0a\u7684\u603b\u529b\u77e9\u4ee5\u53ca\u901a\u8fc7\u73af\u901a\u9053\u7684\u6d41\u91cf\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("turbocharger_transient_analysis.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
