/*
 * coupled_rotors.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:49 by COMSOL 6.3.0.290. */
public class coupled_rotors {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Rotordynamics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("rotbm", "BeamRotor", "geom1");

    model.study().create("std1");
    model.study("std1").create("eig", "Eigenfrequency");
    model.study("std1").feature("eig").setSolveFor("/physics/rotbm", true);

    model.param().set("Ow", "0[rpm]");
    model.param().descr("Ow", "\u8f74\u7684\u89d2\u901f\u5ea6");
    model.param().set("rho_shaft", "7832[kg/m^3]");
    model.param().descr("rho_shaft", "\u8f74\u7684\u5bc6\u5ea6");
    model.param().set("E_shaft", "206820[MPa]");
    model.param().descr("E_shaft", "\u8f74\u7684\u6768\u6c0f\u6a21\u91cf");
    model.param().set("G_shaft", "79546[MPa]");
    model.param().descr("G_shaft", "\u8f74\u7684\u526a\u5207\u6a21\u91cf");
    model.param().set("d_shaft", "12.7[mm]");
    model.param().descr("d_shaft", "\u8f74\u5f84");
    model.param().set("m_disk", "1.27[kg]");
    model.param().descr("m_disk", "\u5706\u76d8\u8d28\u91cf");
    model.param().set("Ip_disk", "0.00256[kg*m^2]");
    model.param().descr("Ip_disk", "\u5706\u76d8\u7684\u6781\u60ef\u6027\u77e9");
    model.param().set("Id_disk", "0.00128[kg*m^2]");
    model.param().descr("Id_disk", "\u5706\u76d8\u7684\u5f84\u5411\u60ef\u6027\u77e9");
    model.param().set("m_coupling", "0.7[kg]");
    model.param().descr("m_coupling", "\u8026\u5408\u7684\u8d28\u91cf");
    model.param().set("m_pulley", "0.87[kg]");
    model.param().descr("m_pulley", "\u6ed1\u8f6e\u7684\u8d28\u91cf");
    model.param().set("l1", "88.9[mm]");
    model.param().descr("l1", "\u5706\u76d8\u4e0e\u5939\u7d27\u7aef\u7684\u8ddd\u79bb");
    model.param().set("l2", "76.2[mm]");
    model.param().descr("l2", "\u8026\u5408\u7aef\u4e0e\u5706\u76d8\u7684\u8ddd\u79bb");
    model.param().set("x_disk", "l1");
    model.param().descr("x_disk", "\u5706\u76d8\u7684\u4f4d\u7f6e");
    model.param().set("x_coupling", "l1+l2");
    model.param().descr("x_coupling", "\u8026\u5408\u7684\u4f4d\u7f6e");
    model.param().set("x_support1", "l1+2*l2");
    model.param().descr("x_support1", "\u7b2c\u4e00\u4e2a\u9500\u4f4f\u652f\u67b6\u7684\u4f4d\u7f6e");
    model.param().set("x_pulley", "l1+3*l2");
    model.param().descr("x_pulley", "\u6ed1\u8f6e\u7684\u4f4d\u7f6e");
    model.param().set("x_support2", "l1+4*l2");
    model.param().descr("x_support2", "\u7b2c\u4e8c\u4e2a\u9500\u4f4f\u652f\u67b6\u7684\u4f4d\u7f6e");

    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol1")
         .set("x", "0 x_disk x_coupling x_support1 x_pulley x_support2");
    model.component("comp1").geom("geom1").feature("pol1").set("y", 0);
    model.component("comp1").geom("geom1").feature("pol1").set("z", 0);
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("rotbm").prop("RotorProperties").set("freqr", "Ow");
    model.component("comp1").physics("rotbm").feature("lemm1").set("IsotropicOption", "EG");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("EG", "EG", "Young's_modulus_and_shear_modulus");
    model.component("comp1").material("mat1").propertyGroup("EG").set("E", new String[]{"E_shaft"});
    model.component("comp1").material("mat1").propertyGroup("EG").set("G", new String[]{"G_shaft"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rho_shaft"});

    model.component("comp1").physics("rotbm").feature("rcs1").set("do_circ", "d_shaft");
    model.component("comp1").physics("rotbm").create("disk1", "Disk", 0);
    model.component("comp1").physics("rotbm").feature("disk1").selection().set(2);
    model.component("comp1").physics("rotbm").feature("disk1").set("mass", "m_disk");
    model.component("comp1").physics("rotbm").feature("disk1").set("Ip", "Ip_disk");
    model.component("comp1").physics("rotbm").feature("disk1").set("Id", "Id_disk");
    model.component("comp1").physics("rotbm").create("disk2", "Disk", 0);
    model.component("comp1").physics("rotbm").feature("disk2").label("\u5706\u76d8\uff1a\u8026\u5408");
    model.component("comp1").physics("rotbm").feature("disk2").selection().set(3);
    model.component("comp1").physics("rotbm").feature("disk2").set("mass", "m_coupling");
    model.component("comp1").physics("rotbm").feature().duplicate("disk3", "disk2");
    model.component("comp1").physics("rotbm").feature("disk3").label("\u5706\u76d8\uff1a\u6ed1\u8f6e");
    model.component("comp1").physics("rotbm").feature("disk3").selection().set(5);
    model.component("comp1").physics("rotbm").feature("disk3").set("mass", "m_pulley");
    model.component("comp1").physics("rotbm").create("jrb1", "JournalBearing", 0);
    model.component("comp1").physics("rotbm").feature("jrb1").label("\u8f74\u9888\u8f74\u627f\uff1a\u56fa\u5b9a");
    model.component("comp1").physics("rotbm").feature("jrb1").selection().set(1);
    model.component("comp1").physics("rotbm").feature().duplicate("jrb2", "jrb1");
    model.component("comp1").physics("rotbm").feature("jrb2").label("\u8f74\u9888\u8f74\u627f\uff1a\u9500\u4f4f");
    model.component("comp1").physics("rotbm").feature("jrb2").selection().set(4, 6);
    model.component("comp1").physics("rotbm").feature("jrb2").set("constrainBendingRotation", false);
    model.component("comp1").physics("rotbm").create("cpl1", "RotorCoupling", 0);
    model.component("comp1").physics("rotbm").feature("cpl1").selection().set(3);

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "Ow", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "1/s", 0);
    model.study("std1").feature("param").setIndex("pname", "Ow", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "1/s", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(0,2000,50000)", 0);
    model.study("std1").feature("param").setIndex("punit", "rpm", 0);
    model.study("std1").feature("eig").set("neigsactive", true);
    model.study("std1").feature("eig").set("neigs", 10);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").setIndex("looplevel", 26, 1);
    model.result("pg1").label("\u6da1\u52a8 (rotbm)");
    model.result("pg1").create("wp1", "Whirl");
    model.result("pg1").feature("wp1").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("wp1").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").feature("wp1").set("nplanes", "1");
    model.result("pg1").feature("wp1").set("nrings", "10");
    model.result("pg1").feature("wp1").set("colortable", "TrafficLight");
    model.result("pg1").feature("wp1").set("ringcolor", "blue");
    model.result("pg1").feature("wp1").selection().geom("geom1", 1);
    model.result("pg1").feature("wp1").selection().set(1, 2, 3, 4, 5);
    model.result("pg1").feature("wp1").selection().inherit(false);
    model.result("pg1").feature("wp1").selection().embedded(false);
    model.result().evaluationGroup().create("std1EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std1EvgFrq").set("data", "dset2");
    model.result().evaluationGroup("std1EvgFrq").label("\u7279\u5f81\u9891\u7387 (\u7814\u7a76 1)");
    model.result().evaluationGroup("std1EvgFrq").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "2*pi*freq", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "rad/s", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u89d2\u9891\u7387", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "imag(freq)/abs(freq)", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u963b\u5c3c\u6bd4", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "abs(freq)/imag(freq)/2", 2);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u54c1\u8d28\u56e0\u5b50", 2);
    model.result().evaluationGroup("std1EvgFrq").run();
    model.result("pg1").create("line1", "Line");
    model.result("pg1").feature("line1").set("expr", new String[]{"1"});
    model.result("pg1").feature("line1").set("linetype", "tube");
    model.result("pg1").feature("line1").set("radiusexpr", new String[]{"rotbm.re "});
    model.result("pg1").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg1").feature("line1").set("tuberadiusscale", 1);
    model.result("pg1").feature("line1").set("tubeendcaps", false);
    model.result("pg1").feature("line1").set("coloring", "uniform");
    model.result("pg1").feature("line1").set("color", "custom");
    model.result("pg1").feature("line1")
         .set("customcolor", new double[]{0.9803921580314636, 0.7843137383460999, 0.7058823704719543});
    model.result("pg1").feature("line1").set("threshold", "manual");
    model.result("pg1").feature("line1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("line1").set("titletype", "none");
    model.result("pg1").feature("line1").label("\u8f6c\u5b50");
    model.result("pg1").feature("line1").create("def", "Deform");
    model.result("pg1").feature("line1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("line1").feature("def").set("scale", "1");
    model.result("pg1").feature("line1").feature("def")
         .set("expr", new String[]{"-rotbm.De_max*rotbm.e30x ", "-rotbm.De_max*rotbm.e30y ", "-rotbm.De_max*rotbm.e30z "});
    model.result("pg1").create("pttraj1", "PointTrajectories");
    model.result("pg1").feature("pttraj1").set("plotdata", "points");
    model.result("pg1").feature("pttraj1").selection().geom("geom1", 0);
    model.result("pg1").feature("pttraj1").selection().set(2);
    model.result("pg1").feature("pttraj1").selection().inherit(false);
    model.result("pg1").feature("pttraj1").selection().embedded(false);
    model.result("pg1").feature("pttraj1").set("linetype", "none");
    model.result("pg1").feature("pttraj1").set("expr", new String[]{"X", "Y", "Z"});
    model.result("pg1").feature("pttraj1").set("pointtype", "ellipse");
    model.result("pg1").feature("pttraj1").set("pointcolor", "custom");
    model.result("pg1").feature("pttraj1")
         .set("custompointcolor", new double[]{0.8039215803146362, 0.5215686559677124, 0.24705882370471954});
    model.result("pg1").feature("pttraj1")
         .set("semimajorexpr", new String[]{"0.5*rotbm.disk1.de*rotbm.e20x ", "0.5*rotbm.disk1.de*rotbm.e20y ", "0.5*rotbm.disk1.de*rotbm.e20z "});
    model.result("pg1").feature("pttraj1")
         .set("semiminorexpr", new String[]{"0.5*rotbm.disk1.de*rotbm.e30x ", "0.5*rotbm.disk1.de*rotbm.e30y ", "0.5*rotbm.disk1.de*rotbm.e30z "});
    model.result("pg1").feature("pttraj1").set("ellipsecount", 1);
    model.result("pg1").feature("pttraj1").set("ellipsearrowscaleactive", true);
    model.result("pg1").feature("pttraj1").set("ellipsearrowtype", "none");
    model.result("pg1").feature("pttraj1").set("titletype", "none");
    model.result("pg1").feature("pttraj1").label("\u5706\u76d8 1");
    model.result("pg1").feature("pttraj1").create("def", "Deform");
    model.result("pg1").feature("pttraj1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("pttraj1").feature("def").set("scale", "1");
    model.result("pg1").feature("pttraj1").feature("def")
         .set("expr", new String[]{"-rotbm.De_max*rotbm.e30x ", "-rotbm.De_max*rotbm.e30y ", "-rotbm.De_max*rotbm.e30z "});
    model.result("pg1").create("pttraj2", "PointTrajectories");
    model.result("pg1").feature("pttraj2").set("plotdata", "points");
    model.result("pg1").feature("pttraj2").selection().geom("geom1", 0);
    model.result("pg1").feature("pttraj2").selection().set(3);
    model.result("pg1").feature("pttraj2").selection().inherit(false);
    model.result("pg1").feature("pttraj2").selection().embedded(false);
    model.result("pg1").feature("pttraj2").set("linetype", "none");
    model.result("pg1").feature("pttraj2").set("expr", new String[]{"X", "Y", "Z"});
    model.result("pg1").feature("pttraj2").set("pointtype", "ellipse");
    model.result("pg1").feature("pttraj2").set("pointcolor", "custom");
    model.result("pg1").feature("pttraj2")
         .set("custompointcolor", new double[]{0.8705882430076599, 0.7215686440467834, 0.529411792755127});
    model.result("pg1").feature("pttraj2")
         .set("semimajorexpr", new String[]{"2*rotbm.re*rotbm.e20x ", "2*rotbm.re*rotbm.e20y ", "2*rotbm.re*rotbm.e20z "});
    model.result("pg1").feature("pttraj2")
         .set("semiminorexpr", new String[]{"2*rotbm.re*rotbm.e30x ", "2*rotbm.re*rotbm.e30y ", "2*rotbm.re*rotbm.e30z "});
    model.result("pg1").feature("pttraj2").set("ellipsecount", 1);
    model.result("pg1").feature("pttraj2").set("ellipsearrowscaleactive", true);
    model.result("pg1").feature("pttraj2").set("ellipsearrowtype", "none");
    model.result("pg1").feature("pttraj2").set("titletype", "none");
    model.result("pg1").feature("pttraj2").label("\u5706\u76d8\uff1a\u8026\u5408");
    model.result("pg1").feature("pttraj2").create("def", "Deform");
    model.result("pg1").feature("pttraj2").feature("def").set("scaleactive", true);
    model.result("pg1").feature("pttraj2").feature("def").set("scale", "1");
    model.result("pg1").feature("pttraj2").feature("def")
         .set("expr", new String[]{"-rotbm.De_max*rotbm.e30x ", "-rotbm.De_max*rotbm.e30y ", "-rotbm.De_max*rotbm.e30z "});
    model.result("pg1").create("pttraj3", "PointTrajectories");
    model.result("pg1").feature("pttraj3").set("plotdata", "points");
    model.result("pg1").feature("pttraj3").selection().geom("geom1", 0);
    model.result("pg1").feature("pttraj3").selection().set(5);
    model.result("pg1").feature("pttraj3").selection().inherit(false);
    model.result("pg1").feature("pttraj3").selection().embedded(false);
    model.result("pg1").feature("pttraj3").set("linetype", "none");
    model.result("pg1").feature("pttraj3").set("expr", new String[]{"X", "Y", "Z"});
    model.result("pg1").feature("pttraj3").set("pointtype", "ellipse");
    model.result("pg1").feature("pttraj3").set("pointcolor", "custom");
    model.result("pg1").feature("pttraj3")
         .set("custompointcolor", new double[]{0.8705882430076599, 0.7215686440467834, 0.529411792755127});
    model.result("pg1").feature("pttraj3")
         .set("semimajorexpr", new String[]{"2*rotbm.re*rotbm.e20x ", "2*rotbm.re*rotbm.e20y ", "2*rotbm.re*rotbm.e20z "});
    model.result("pg1").feature("pttraj3")
         .set("semiminorexpr", new String[]{"2*rotbm.re*rotbm.e30x ", "2*rotbm.re*rotbm.e30y ", "2*rotbm.re*rotbm.e30z "});
    model.result("pg1").feature("pttraj3").set("ellipsecount", 1);
    model.result("pg1").feature("pttraj3").set("ellipsearrowscaleactive", true);
    model.result("pg1").feature("pttraj3").set("ellipsearrowtype", "none");
    model.result("pg1").feature("pttraj3").set("titletype", "none");
    model.result("pg1").feature("pttraj3").label("\u5706\u76d8\uff1a\u6ed1\u8f6e");
    model.result("pg1").feature("pttraj3").create("def", "Deform");
    model.result("pg1").feature("pttraj3").feature("def").set("scaleactive", true);
    model.result("pg1").feature("pttraj3").feature("def").set("scale", "1");
    model.result("pg1").feature("pttraj3").feature("def")
         .set("expr", new String[]{"-rotbm.De_max*rotbm.e30x ", "-rotbm.De_max*rotbm.e30y ", "-rotbm.De_max*rotbm.e30z "});
    model.result("pg1").create("pttraj4", "PointTrajectories");
    model.result("pg1").feature("pttraj4").set("plotdata", "points");
    model.result("pg1").feature("pttraj4").selection().geom("geom1", 0);
    model.result("pg1").feature("pttraj4").selection().set(1);
    model.result("pg1").feature("pttraj4").selection().inherit(false);
    model.result("pg1").feature("pttraj4").selection().embedded(false);
    model.result("pg1").feature("pttraj4").set("linetype", "none");
    model.result("pg1").feature("pttraj4").set("pointtype", "arrow");
    model.result("pg1").feature("pttraj4")
         .set("expr", new String[]{"X-1.0*rotbm.re*rotbm.jrb1.e3gx ", "Y-1.0*rotbm.re*rotbm.jrb1.e3gy ", "Z-1.0*rotbm.re*rotbm.jrb1.e3gz "});
    model.result("pg1").feature("pttraj4")
         .set("arrowexpr", new String[]{"rotbm.re*rotbm.jrb1.e3gx ", "rotbm.re*rotbm.jrb1.e3gy ", "rotbm.re*rotbm.jrb1.e3gz "});
    model.result("pg1").feature("pttraj4").set("arrowtype", "arrowhead");
    model.result("pg1").feature("pttraj4").set("arrowbase", "head");
    model.result("pg1").feature("pttraj4").set("arrowscale", "10");
    model.result("pg1").feature("pttraj4").set("arrowscaleactive", true);
    model.result("pg1").feature("pttraj4").set("pointcolor", "custom");
    model.result("pg1").feature("pttraj4")
         .set("custompointcolor", new double[]{0.5882353186607361, 0.8627451062202454, 0.5882353186607361});
    model.result("pg1").feature("pttraj4").set("titletype", "none");
    model.result("pg1").feature("pttraj4").label("\u8f74\u9888\u8f74\u627f\uff1a\u56fa\u5b9a");
    model.result("pg1").feature("pttraj4").create("def", "Deform");
    model.result("pg1").feature("pttraj4").feature("def").set("scaleactive", true);
    model.result("pg1").feature("pttraj4").feature("def").set("scale", "1");
    model.result("pg1").feature("pttraj4").feature("def")
         .set("expr", new String[]{"-rotbm.De_max*rotbm.e30x ", "-rotbm.De_max*rotbm.e30y ", "-rotbm.De_max*rotbm.e30z "});
    model.result("pg1").create("pttraj5", "PointTrajectories");
    model.result("pg1").feature("pttraj5").set("plotdata", "points");
    model.result("pg1").feature("pttraj5").selection().geom("geom1", 0);
    model.result("pg1").feature("pttraj5").selection().set(4, 6);
    model.result("pg1").feature("pttraj5").selection().inherit(false);
    model.result("pg1").feature("pttraj5").selection().embedded(false);
    model.result("pg1").feature("pttraj5").set("linetype", "none");
    model.result("pg1").feature("pttraj5").set("pointtype", "arrow");
    model.result("pg1").feature("pttraj5")
         .set("expr", new String[]{"X-1.0*rotbm.re*rotbm.jrb2.e3gx ", "Y-1.0*rotbm.re*rotbm.jrb2.e3gy ", "Z-1.0*rotbm.re*rotbm.jrb2.e3gz "});
    model.result("pg1").feature("pttraj5")
         .set("arrowexpr", new String[]{"rotbm.re*rotbm.jrb2.e3gx ", "rotbm.re*rotbm.jrb2.e3gy ", "rotbm.re*rotbm.jrb2.e3gz "});
    model.result("pg1").feature("pttraj5").set("arrowtype", "arrowhead");
    model.result("pg1").feature("pttraj5").set("arrowbase", "head");
    model.result("pg1").feature("pttraj5").set("arrowscale", "10");
    model.result("pg1").feature("pttraj5").set("arrowscaleactive", true);
    model.result("pg1").feature("pttraj5").set("pointcolor", "custom");
    model.result("pg1").feature("pttraj5")
         .set("custompointcolor", new double[]{0.5882353186607361, 0.8627451062202454, 0.5882353186607361});
    model.result("pg1").feature("pttraj5").set("titletype", "none");
    model.result("pg1").feature("pttraj5").label("\u8f74\u9888\u8f74\u627f\uff1a\u9500\u4f4f");
    model.result("pg1").feature("pttraj5").create("def", "Deform");
    model.result("pg1").feature("pttraj5").feature("def").set("scaleactive", true);
    model.result("pg1").feature("pttraj5").feature("def").set("scale", "1");
    model.result("pg1").feature("pttraj5").feature("def")
         .set("expr", new String[]{"-rotbm.De_max*rotbm.e30x ", "-rotbm.De_max*rotbm.e30y ", "-rotbm.De_max*rotbm.e30z "});
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{3, 26});
    model.result("pg1").run();
    model.result("pg1").feature("wp1").set("nplanes", 6);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").create("lnsg1", "LineSegments");
    model.result("pg2").feature("lnsg1").set("data", "dset2");
    model.result("pg2").feature("lnsg1").set("looplevelinput", new String[][]{{"last", "last"}});
    model.result("pg2").feature("lnsg1").set("xexpr", new String[]{"0[Hz]", "min(real(freq),rotbm.freqr)"});
    model.result("pg2").feature("lnsg1").set("xunit", new String[]{"Hz", "Hz"});
    model.result("pg2").feature("lnsg1").set("xdescr", new String[]{"\u539f\u70b9", "\u89d2\u901f\u5ea6"});
    model.result("pg2").feature("lnsg1").set("yexpr", new String[]{"0[Hz]", "min(real(freq),rotbm.freqr)"});
    model.result("pg2").feature("lnsg1").set("yunit", new String[]{"Hz", "Hz"});
    model.result("pg2").feature("lnsg1").set("ydescr", new String[]{"\u539f\u70b9", "\u89d2\u901f\u5ea6"});
    model.result("pg2").feature("lnsg1").set("linestyle", "cycle");
    model.result("pg2").feature("lnsg1").set("linecolor", "black");
    model.result("pg2").feature("lnsg1").set("linewidth", 1);
    model.result("pg2").feature("lnsg1").set("legend", true);
    model.result("pg2").feature("lnsg1").set("legendmethod", "manual");
    model.result("pg2").feature("lnsg1").set("legends", "1\\times\\Omega");
    model.result("pg2").feature("lnsg1").label("\u8f85\u52a9\u7ebf (1\u00d7\u03a9)");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("expr", "real(freq)");
    model.result("pg2").feature("glob1").set("descr", "\u963b\u5c3c\u56fa\u6709\u9891\u7387");
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").feature("glob1").set("xdataexpr", "rotbm.freqr");
    model.result("pg2").feature("glob1").set("xdataunit", "Hz");
    model.result("pg2").feature("glob1").set("xdatadescr", "\u65cb\u8f6c\u901f\u5ea6");
    model.result("pg2").feature("glob1").set("linestyle", "solid");
    model.result("pg2").feature("glob1").set("linecolor", "cycle");
    model.result("pg2").feature("glob1").set("linewidth", 2);
    model.result("pg2").feature("glob1").set("legend", false);
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "outer");
    model.result("pg2").feature("glob1").label("\u56fa\u6709\u9891\u7387");
    model.result("pg2").feature("glob1").create("col1", "Color");
    model.result("pg2").feature("glob1").feature("col1").set("expr", "rotbm.i_sd");
    model.result("pg2").feature("glob1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("glob1").feature("col1").label("\u65b9\u5411\u6027");
    model.result("pg2").feature("glob1").feature("col1").set("colortable", "Spectrum");
    model.result("pg2").feature("glob1").feature("col1").set("rangecoloractive", true);
    model.result("pg2").feature("glob1").feature("col1").set("rangecolormin", -1.3);
    model.result("pg2").feature("glob1").feature("col1").set("rangecolormax", 1.3);
    model.result("pg2").feature("glob1").feature("col1").set("colorscalemode", "linearsymmetric");
    model.result("pg2").feature("glob1").create("gmrk1", "GraphMarker");
    model.result("pg2").feature("glob1").feature("gmrk1").set("displaymode", "intersection");
    model.result("pg2").feature("glob1").feature("gmrk1").set("intersectionline", "general");
    model.result("pg2").feature("glob1").feature("gmrk1").set("generalcoeffc", 0);
    model.result("pg2").feature("glob1").feature("gmrk1").set("generalcoeffa", " -1");
    model.result("pg2").feature("glob1").feature("gmrk1").set("generalcoeffb", 1);
    model.result("pg2").feature("glob1").feature("gmrk1").set("includexcoord", false);
    model.result("pg2").feature("glob1").feature("gmrk1").set("includeycoord", false);
    model.result("pg2").feature("glob1").feature("gmrk1").set("precision", 3);
    model.result("pg2").feature("glob1").feature("gmrk1").set("pointradius", 2.5);
    model.result("pg2").feature("glob1").feature("gmrk1").set("anchorpoint", "lowermiddle");
    model.result("pg2").feature("glob1").feature("gmrk1").label("\u4ea4\u96c6 (1\u00d7\u03a9)");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u574e\u8d1d\u5c14\u56fe");
    model.result("pg2").label("\u574e\u8d1d\u5c14\u56fe (rotbm)");
    model.result("pg2").label("\u574e\u8d1d\u5c14\u56fe (rotbm)");
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevelinput", "manualindices", 0);
    model.result("pg2").setIndex("looplevelindices", "range(1,1,8)", 0);
    model.result("pg2").set("axislimits", true);
    model.result("pg2").set("ymax", 1000);
    model.result("pg2").set("ymin", -20);
    model.result("pg2").run();
    model.result("pg2").feature("glob1").setIndex("expr", "abs(freq)", 0);
    model.result("pg2").feature("glob1").setIndex("descr", "Natural frequency", 0);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u632f\u578b");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").set("looplevel", new int[]{6, 5});
    model.result("pg3").create("line1", "Line");
    model.result("pg3").feature("line1").set("linetype", "tube");
    model.result("pg3").feature("line1").set("radiusexpr", "rotbm.re");
    model.result("pg3").feature("line1").set("colortable", "AuroraAustralis");
    model.result("pg3").feature("line1").set("colorlegend", false);
    model.result("pg3").feature("line1").create("def1", "Deform");
    model.result("pg3").run();
    model.result("pg3").feature("line1").feature("def1").set("scaleactive", true);
    model.result("pg3").feature("line1").feature("def1").set("scale", 0.3);
    model.result("pg3").run();
    model.result("pg1").run();

    model.title("\u82b1\u952e\u8054\u8f74\u5668\u8fde\u63a5\u7684\u8f6c\u5b50");

    model
         .description("\u5728\u672c\u4f8b\u4e2d\uff0c\u60a8\u5c06\u4e86\u89e3\u5982\u4f55\u6a21\u62df\u901a\u8fc7\u82b1\u952e\u8054\u8f74\u5668\u8fde\u63a5\u7684\u4e24\u4e2a\u8f6c\u5b50\uff0c\u5176\u4e2d\u7b2c\u4e00\u4e2a\u8f6c\u5b50\u662f\u56fa\u5b9a\u7684\u60ac\u81c2\u8f6c\u5b50\uff0c\u7b2c\u4e8c\u4e2a\u8f6c\u5b50\u4e3a\u7b80\u652f\u8f6c\u5b50\u3002\u5047\u8bbe\u8f6c\u5b50\u4e4b\u95f4\u4ec5\u901a\u8fc7\u8054\u8f74\u5668\u8026\u5408\u5e73\u79fb\u8fd0\u52a8\uff0c\u800c\u4e24\u4e2a\u8f6c\u5b50\u7684\u65cb\u8f6c\u8fd0\u52a8\u672a\u8026\u5408\u3002\n\n\u672c\u6559\u7a0b\u6f14\u793a\u5982\u4f55\u5728\u4e0d\u540c\u7684\u8f6c\u901f\u4e0b\u6267\u884c\u7279\u5f81\u9891\u7387\u5206\u6790\uff0c\u5176\u4e2d\uff0c\u8f6c\u5b50\u7684\u9640\u87ba\u6548\u5e94\u4f1a\u589e\u5f3a\u6b63\u5411\u6a21\u6001\uff0c\u5e76\u51cf\u5f31\u53cd\u5411\u6a21\u6001\u3002\u672c\u4f8b\u901a\u8fc7\u574e\u8d1d\u5c14\u56fe\u6765\u5c55\u793a\u4eff\u771f\u7ed3\u679c\uff0c\u5e76\u5c06\u4e34\u754c\u8f6c\u901f\u4e0e\u53c2\u8003\u6587\u732e\u4e2d\u83b7\u5f97\u7684\u7ed3\u679c\u8fdb\u884c\u6bd4\u8f83\uff0c\u4e24\u8005\u975e\u5e38\u63a5\u8fd1\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();
    model.sol("sol11").clearSolutionData();
    model.sol("sol12").clearSolutionData();
    model.sol("sol13").clearSolutionData();
    model.sol("sol14").clearSolutionData();
    model.sol("sol15").clearSolutionData();
    model.sol("sol16").clearSolutionData();
    model.sol("sol17").clearSolutionData();
    model.sol("sol18").clearSolutionData();
    model.sol("sol19").clearSolutionData();
    model.sol("sol20").clearSolutionData();
    model.sol("sol21").clearSolutionData();
    model.sol("sol22").clearSolutionData();
    model.sol("sol23").clearSolutionData();
    model.sol("sol24").clearSolutionData();
    model.sol("sol25").clearSolutionData();
    model.sol("sol26").clearSolutionData();
    model.sol("sol27").clearSolutionData();
    model.sol("sol28").clearSolutionData();

    model.label("coupled_rotors.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
