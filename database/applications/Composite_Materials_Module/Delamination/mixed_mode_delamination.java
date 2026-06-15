/*
 * mixed_mode_delamination.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:31 by COMSOL 6.3.0.290. */
public class mixed_mode_delamination {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Composite_Materials_Module\\Delamination");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("lb", "102[mm]", "Length");
    model.param().set("wb", "25.4[mm]", "Width");
    model.param().set("hb", "2*1.56[mm]", "Thickness");
    model.param().set("cl", "34.1[mm]", "Initial crack length");
    model.param().set("pn", "1e6[N/mm^3]", "Penalty stiffness");
    model.param().set("sigmat", "80[MPa]", "Normal tensile strength");
    model.param().set("sigmas", "100[MPa]", "Shear strength");
    model.param().set("Gct", "0.969[kJ/m^2]", "Critical energy release rate, tension");
    model.param().set("Gcs", "1.719[kJ/m^2]", "Critical energy release rate, shear");
    model.param().set("alpha", "2.284", "Exponent of the Benzeggagh and Kenane (B-K) criterion");
    model.param().set("disp", "0", "Displacement parameter");
    model.param().set("mm", "0.5", "Mode mixity ratio");
    model.param().set("ll", "lb/2*(0.5*sqrt(3*(1-mm)/mm)+1)/(3-0.5*sqrt(3*(1-mm)/mm))", "Lever length");
    model.param()
         .set("lr", "8*((6*mm+sqrt(3*mm*(1-mm)))/(3+9*mm+8*sqrt(3*mm*(1-mm))))", "Load ratio middle/cracked edge");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"lb", "wb/2", "hb"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", "hb/2", 0);
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"lb/2-cl", "wb/2", "hb"});
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"cl", "0", "0"});
    model.component("comp1").geom("geom1").feature("blk2").setIndex("layer", "hb/2", 0);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").coordSystem("sys1").set("frametype", "material");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").label("Load Point Variables");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("u_Ie", "intop1(w)", "Displacement at edge");
    model.component("comp1").variable("var1").set("w_c", "intop2(w)", "Displacement at center");
    model.component("comp1").variable("var1")
         .set("u_lp", "(3*ll-lb/2)/4/(lb/2)*u_Ie+((ll+lb/2)/(lb/2))*(-w_c+u_Ie/4)", "Load point displacement");
    model.component("comp1").variable("var1").set("F_lp", "force*lb/2/ll", "Load point force");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").label("Integration Edge");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().set(3);
    model.component("comp1").cpl("intop1").set("frame", "material");
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").label("Integration Center");
    model.component("comp1").cpl("intop2").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop2").selection().set(15);
    model.component("comp1").cpl("intop2").set("frame", "material");

    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup()
         .create("TransverseIsotropic", "TransverseIsotropic", "Transversely isotropic");
    model.material("mat1")
         .label("Unidirectional fiber lamina: AS4/APC2 carbon/PEEK thermoplastic [fiber volume fraction 50%]");
    model.material("mat1").propertyGroup("def").set("density", "1570[kg/m^3]");
    model.material("mat1").propertyGroup("def")
         .setPropertyInfo("density", "Reference: P.P. Camanho, C.G. Davila, and M.F. De Moura, Numerical Simulation of Mixed Mode Progressive Delamination in Composite Materials, Journal of composite materials, vol. 37, no. 16, 2003.");
    model.material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"-0.2E-6[1/K]", "0", "0", "0", "24E-6[1/K]", "0", "0", "0", "24E-6[1/K]"});
    model.material("mat1").propertyGroup("TransverseIsotropic")
         .set("Evect", new String[]{"122.7[GPa]", "10.1[GPa]"});
    model.material("mat1").propertyGroup("TransverseIsotropic")
         .setPropertyInfo("Evect", "Reference: P.P. Camanho, C.G. Davila, and M.F. De Moura, Numerical Simulation of Mixed Mode Progressive Delamination in Composite Materials, Journal of composite materials, vol. 37, no. 16, 2003.");
    model.material("mat1").propertyGroup("TransverseIsotropic").set("nuvect", new String[]{"0.25", "0.45"});
    model.material("mat1").propertyGroup("TransverseIsotropic")
         .setPropertyInfo("nuvect", "Reference: P.P. Camanho, C.G. Davila, and M.F. De Moura, Numerical Simulation of Mixed Mode Progressive Delamination in Composite Materials, Journal of composite materials, vol. 37, no. 16, 2003.");
    model.material("mat1").propertyGroup("TransverseIsotropic").set("Gvect1", "5.5[GPa]");
    model.material("mat1").propertyGroup("TransverseIsotropic")
         .setPropertyInfo("Gvect1", "Reference: P.P. Camanho, C.G. Davila, and M.F. De Moura, Numerical Simulation of Mixed Mode Progressive Delamination in Composite Materials, Journal of composite materials, vol. 37, no. 16, 2003.");
    model.component("comp1").material().create("matlnk1", "Link");

    model.component("comp1").physics("solid").feature("lemm1").set("SolidModel", "Orthotropic");
    model.component("comp1").physics("solid").create("icnt1", "InteriorContact", 2);
    model.component("comp1").physics("solid").feature("icnt1").selection().set(15, 24);
    model.component("comp1").physics("solid").feature("icnt1").set("penaltyCtrlPenalty", "userDefined");
    model.component("comp1").physics("solid").feature("icnt1").set("pn_penalty", "pn");
    model.component("comp1").physics("solid").feature("icnt1").create("adh1", "Adhesion", 2);
    model.component("comp1").physics("solid").feature("icnt1").feature("adh1").set("ActivationCriterion", "Always");
    model.component("comp1").physics("solid").feature("icnt1").feature("adh1").set("ntau", 1);
    model.component("comp1").physics("solid").feature("icnt1").feature("adh1").create("dch1", "Decohesion", 2);
    model.component("comp1").physics("solid").feature("icnt1").feature("adh1").feature("dch1")
         .set("sigmat", "sigmat");
    model.component("comp1").physics("solid").feature("icnt1").feature("adh1").feature("dch1")
         .set("sigmas", "sigmas");
    model.component("comp1").physics("solid").feature("icnt1").feature("adh1").feature("dch1").set("Gct", "Gct");
    model.component("comp1").physics("solid").feature("icnt1").feature("adh1").feature("dch1").set("Gcs", "Gcs");
    model.component("comp1").physics("solid").feature("icnt1").feature("adh1").feature("dch1")
         .set("FailureCriterion", "BK");
    model.component("comp1").physics("solid").feature("icnt1").feature("adh1").feature("dch1").set("alpha", "alpha");
    model.component("comp1").physics("solid").create("asl1", "AuxiliarySlit", 2);
    model.component("comp1").physics("solid").feature("asl1").selection().set(6);
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym1").selection().set(2, 5, 11, 14, 20, 23);
    model.component("comp1").physics("solid").create("el1", "EdgeLoad", 1);
    model.component("comp1").physics("solid").feature("el1").label("Load on Cracked Edge (Fe)");
    model.component("comp1").physics("solid").feature("el1").selection().set(7);
    model.component("comp1").physics("solid").feature("el1").set("forceType", "TotalForce");
    model.component("comp1").physics("solid").feature("el1").set("force", new String[]{"0", "0", "force"});
    model.component("comp1").physics("solid").create("el2", "EdgeLoad", 1);
    model.component("comp1").physics("solid").feature("el2").label("Load on Middle Edge (Fm)");
    model.component("comp1").physics("solid").feature("el2").selection().set(33);
    model.component("comp1").physics("solid").feature("el2").set("forceType", "TotalForce");
    model.component("comp1").physics("solid").feature("el2").set("force", new String[]{"0", "0", "-lr*force"});
    model.component("comp1").physics("solid").create("disp1", "Displacement1", 1);
    model.component("comp1").physics("solid").feature("disp1").selection().set(2, 41);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("solid").create("disp2", "Displacement0", 0);
    model.component("comp1").physics("solid").feature("disp2").selection().set(1);
    model.component("comp1").physics("solid").feature("disp2").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("solid").create("ge1", "GlobalEquations", -1);
    model.component("comp1").physics("solid").feature("ge1").setIndex("name", "force", 0, 0);
    model.component("comp1").physics("solid").feature("ge1").setIndex("equation", "disp-u_Ie", 0, 0);
    model.component("comp1").physics("solid").feature("ge1").set("DependentVariableQuantity", "force");
    model.component("comp1").physics("solid").feature("ge1").set("SourceTermQuantity", "displacement");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(2, 5, 11, 14, 20, 23);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(3, 6, 8);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 10);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(16, 19, 21);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 50);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").selection().set(29, 32, 34);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("elemcount", 20);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("elemratio", 5);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 3);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("geometricNonlinearity", true);
    model.study("std1").feature("stat").set("usestol", true);
    model.study("std1").feature("stat").set("stol", "1e-4");
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "lb", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "lb", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "disp", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,2e-4,8e-3)", 0);
    model.study("std1").feature("stat").set("plot", true);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v1").feature("comp1_ODE1").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_ODE1").set("scaleval", 200);
    model.sol("sol1").feature("s1").feature("p1").set("paramtuning", true);
    model.sol("sol1").feature("s1").feature("p1").set("pminstep", "1e-6");
    model.sol("sol1").feature("s1").feature("p1").set("porder", "linear");
    model.sol("sol1").feature("s1").feature("fc1").set("dtech", "const");

    model.study("std1").createAutoSequences("sol");
    model.study("std1").createAutoSequences("jobs");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").label("Stress (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("vol1").set("threshold", "manual");
    model.result("pg1").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("vol1").set("colortable", "Rainbow");
    model.result("pg1").feature("vol1").set("colortabletrans", "none");
    model.result("pg1").feature("vol1").set("colorscalemode", "linear");
    model.result("pg1").feature("vol1").set("resolution", "custom");
    model.result("pg1").feature("vol1").set("refine", 2);
    model.result("pg1").feature("vol1").set("colortable", "Prism");
    model.result("pg1").feature("vol1").create("def", "Deform");
    model.result("pg1").feature("vol1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("vol1").feature("def").set("scale", "1");
    model.result("pg1").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("vol1").feature("def").set("descr", "Displacement field");

    model.sol("sol1").runFromTo("st1", "v1");

    model.result().remove("pg1");

    model.study("std1").feature("stat").set("plotgroup", "Default");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").label("Stress (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("vol1").set("threshold", "manual");
    model.result("pg1").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("vol1").set("colortable", "Rainbow");
    model.result("pg1").feature("vol1").set("colortabletrans", "none");
    model.result("pg1").feature("vol1").set("colorscalemode", "linear");
    model.result("pg1").feature("vol1").set("resolution", "custom");
    model.result("pg1").feature("vol1").set("refine", 2);
    model.result("pg1").feature("vol1").set("colortable", "Prism");
    model.result("pg1").feature("vol1").create("def", "Deform");
    model.result("pg1").feature("vol1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("vol1").feature("def").set("scale", "1");
    model.result("pg1").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("vol1").feature("def").set("descr", "Displacement field");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("vol1").set("unit", "MPa");
    model.result("pg1").feature("vol1").set("elemscale", 0.999);
    model.result("pg1").run();
    model.result("pg1").set("edges", false);
    model.result("pg1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("Interface Health");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "solid.bdmg");
    model.result("pg2").feature("surf1").set("descr", "Damage");
    model.result("pg2").feature("surf1").set("colortable", "Traffic");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("Interface Health: Gauss Points");
    model.result("pg3").create("pt1", "Point");
    model.result("pg3").feature("pt1").set("expr", "solid.bdmg");
    model.result("pg3").feature("pt1").set("level", "surface");
    model.result("pg3").feature("pt1").set("placement", "gausspoints");
    model.result("pg3").feature("pt1").set("gporder", "4");
    model.result("pg3").feature("pt1").set("colortable", "Traffic");
    model.result("pg3").run();
    model.result("pg3").feature("pt1").set("sphereradiusscaleactive", true);
    model.result("pg3").feature("pt1").set("sphereradiusscale", 0.03);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").set("edges", false);
    model.result("pg3").create("mesh1", "Mesh");
    model.result("pg3").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg3").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg3").feature("mesh1").set("titletype", "none");
    model.result("pg3").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg3").feature("mesh1").feature("sel1").selection().set(15, 24);
    model.result("pg3").run();
    model.result("pg3").feature("mesh1").set("elemcolor", "gray");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("Load Displacement Curve");
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "Load displacement curve");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "2*F_lp", 0);
    model.result("pg4").feature("glob1").setIndex("descr", "Load", 0);
    model.result("pg4").feature("glob1").set("legend", false);
    model.result("pg4").feature("glob1").set("xdata", "expr");
    model.result("pg4").feature("glob1").set("xdataexpr", "u_lp");
    model.result("pg4").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("Load (solid)");
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "2*F_lp", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "Load", 0);
    model.result().evaluationGroup("eg1").feature("gev1").set("dataseries", "maximum");
    model.result().evaluationGroup("eg1").run();
    model.result("pg1").run();

    model.title("\u5c42\u538b\u590d\u5408\u6750\u6599\u7684\u6df7\u5408\u6a21\u5f0f\u5265\u79bb");

    model
         .description("\u7531\u4e8e\u8131\u5c42\u6216\u5265\u79bb\u800c\u4ea7\u751f\u7684\u754c\u9762\u7834\u574f\u53ef\u4ee5\u901a\u8fc7\u5185\u805a\u529b\u6a21\u578b (CZM) \u6765\u6a21\u62df\u3002\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u5b9e\u73b0\u9075\u4ece\u53cc\u7ebf\u6027\u62c9\u4f38\u5206\u79bb\u5b9a\u5f8b\u7684 CZM\u3002\u9884\u6d4b\u4e86\u6df7\u5408\u6a21\u5f0f\u8f6f\u5316\u8d77\u70b9\u548c\u590d\u5408\u6750\u6599\u5265\u79bb\u7684\u53d1\u5c55\u3002");

    model.label("cohesive_zone_debonding.mph");

    model.result("pg1").run();

    model.component("comp1").label("\u7ec4\u4ef6 [\u56fa\u4f53\u529b\u5b66]");

    model.study("std1").label("\u7814\u7a76 [\u56fa\u4f53\u529b\u5b66]");

    model.result("pg1").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").add("plotgroup", "pg1");
    model.nodeGroup("grp1").add("plotgroup", "pg2");
    model.nodeGroup("grp1").add("plotgroup", "pg3");
    model.nodeGroup("grp1").label("\u56fa\u4f53\u529b\u5b66\u7ed8\u56fe");

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 3);
    model.component("comp2").geom("geom2").geomRep("comsol");

    model.component("comp2").mesh().create("mesh2");
    model.component("comp2").mesh("mesh2").contribute("geom/detail", true);

    model.component("comp2").label("\u7ec4\u4ef6 [\u591a\u5c42\u58f3]");

    model.component("comp2").geom("geom2").create("wp1", "WorkPlane");
    model.component("comp2").geom("geom2").feature("wp1").set("unite", true);
    model.component("comp2").geom("geom2").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"lb", "wb/2"});
    model.component("comp2").geom("geom2").feature("wp1").geom().run("r1");
    model.component("comp2").geom("geom2").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("r2")
         .set("size", new String[]{"lb/2-cl", "wb/2"});
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("r2").set("pos", new String[]{"cl", "0"});
    model.component("comp2").geom("geom2").feature("wp1").geom().run("r2");
    model.component("comp2").geom("geom2").feature("wp1").geom().create("uni1", "Union");
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("uni1").selection("input").set("r1", "r2");
    model.component("comp2").geom("geom2").run("fin");

    model.component("comp2").cpl().create("intop3", "Integration");
    model.component("comp2").cpl("intop3").set("axisym", true);
    model.component("comp2").cpl("intop3").label("\u79ef\u5206\u8fb9");
    model.component("comp2").cpl("intop3").selection().geom("geom2", 0);
    model.component("comp2").cpl("intop3").selection().set(1);
    model.component("comp2").cpl("intop3").set("frame", "material");
    model.component("comp2").cpl().duplicate("intop4", "intop3");
    model.component("comp2").cpl("intop4").label("\u79ef\u5206\u4e2d\u5fc3");
    model.component("comp2").cpl("intop4").selection().set(5);

    model.component("comp2").variable().create("var2");
    model.component("comp2").variable("var2").label("\u8f7d\u8377\u70b9\u53d8\u91cf");
    model.component("comp2").variable("var2").set("u_IeL", "intop3(lshell.atxd1(hb,w2))");
    model.component("comp2").variable("var2").descr("u_IeL", "\u8fb9\u7684\u4f4d\u79fb [\u591a\u5c42\u58f3]");
    model.component("comp2").variable("var2").set("w_cL", "intop4(lshell.atxd1(hb,w2))");
    model.component("comp2").variable("var2").descr("w_cL", "\u4e2d\u5fc3\u7684\u4f4d\u79fb [\u591a\u5c42\u58f3]");
    model.component("comp2").variable("var2")
         .set("u_lpL", "(3*ll-lb/2)/4/(lb/2)*u_IeL+((ll+lb/2)/(lb/2))*(-w_cL+u_IeL/4)");
    model.component("comp2").variable("var2").descr("u_lpL", "\u8f7d\u8377\u70b9\u4f4d\u79fb [\u591a\u5c42\u58f3]");
    model.component("comp2").variable("var2").set("F_lpL", "forceL*lb/2/ll");
    model.component("comp2").variable("var2").descr("F_lpL", "\u8f7d\u8377\u70b9\u529b [\u591a\u5c42\u58f3]");

    model.material().create("lmat1", "LayeredMaterial", "");
    model.material("lmat1").setIndex("thickness", "hb/2", 0);
    model.material("lmat1").setIndex("layername", "\u5c42 2", 1);
    model.material("lmat1").setIndex("link", "mat1", 1);
    model.material("lmat1").setIndex("rotation", "0.0", 1);
    model.material("lmat1").setIndex("thickness", "hb/2", 1);
    model.material("lmat1").setIndex("meshPoints", 2, 1);
    model.material("lmat1").setIndex("tag", "lmat1_2", 1);
    model.material("lmat1").setIndex("layername", "\u5c42 2", 1);
    model.material("lmat1").setIndex("link", "mat1", 1);
    model.material("lmat1").setIndex("rotation", "0.0", 1);
    model.material("lmat1").setIndex("thickness", "hb/2", 1);
    model.material("lmat1").setIndex("meshPoints", 2, 1);
    model.material("lmat1").setIndex("tag", "lmat1_2", 1);
    model.component("comp2").material().create("llmat1", "LayeredMaterialLink");

    model.component("comp2").coordSystem("sys2").set("mastercoordsystcomp", "1");
    model.component("comp2").coordSystem("sys2").set("frametype", "material");

    model.component("comp2").physics().create("lshell", "LayeredShell", "geom2");

    model.study("std1").feature("stat").setSolveFor("/physics/lshell", true);

    model.component("comp2").physics("lshell").create("del1", "Delamination", 2);
    model.component("comp2").physics("lshell").feature("del1").selection().set(1);
    model.component("comp2").physics("lshell").feature("del1").set("InitialState", "Delaminated");
    model.component("comp2").physics("lshell").feature("del1").set("pn", "pn");
    model.component("comp2").physics("lshell").create("del2", "Delamination", 2);
    model.component("comp2").physics("lshell").feature("del2").selection().set(2, 3);
    model.component("comp2").physics("lshell").feature("del2").set("StiffnessInput", "UserDefined");
    model.component("comp2").physics("lshell").feature("del2").set("kPerArea", new String[]{"pn", "pn", "pn"});
    model.component("comp2").physics("lshell").feature("del2").set("sigmat", "sigmat");
    model.component("comp2").physics("lshell").feature("del2").set("sigmas", "sigmas");
    model.component("comp2").physics("lshell").feature("del2").set("Gct", "Gct");
    model.component("comp2").physics("lshell").feature("del2").set("Gcs", "Gcs");
    model.component("comp2").physics("lshell").feature("del2").set("FailureCriterion", "BK");
    model.component("comp2").physics("lshell").feature("del2").set("alpha", "alpha");
    model.component("comp2").physics("lshell").feature("del2").set("PenaltyFactor", "FromAdhesiveStiffness");
    model.component("comp2").physics("lshell").create("sym1", "Symmetry", 1);
    model.component("comp2").physics("lshell").feature("sym1").selection().set(2, 5, 8);
    model.component("comp2").physics("lshell").create("el1", "EdgeLoad", 1);
    model.component("comp2").physics("lshell").feature("el1")
         .label("\u88c2\u7eb9\u8fb9\u4e0a\u7684\u8f7d\u8377 (Fe)");
    model.component("comp2").physics("lshell").feature("el1").set("forceType", "TotalForce");
    model.component("comp2").physics("lshell").feature("el1").selection().set(1);
    model.component("comp2").physics("lshell").feature("el1").set("force", new String[]{"0", "0", "forceL"});
    model.component("comp2").physics("lshell").feature().duplicate("el2", "el1");
    model.component("comp2").physics("lshell").feature("el2")
         .label("\u4e2d\u95f4\u8fb9\u4e0a\u7684\u8f7d\u8377 (Fm)");
    model.component("comp2").physics("lshell").feature("el2").selection().set(7);
    model.component("comp2").physics("lshell").feature("el2").set("force", new String[]{"0", "0", "-lr*forceL"});
    model.component("comp2").physics("lshell").create("dispi1", "DisplacementIntEP", 1);
    model.component("comp2").physics("lshell").feature("dispi1").selection().set(1, 10);
    model.component("comp2").physics("lshell").feature("dispi1").set("applyTo", "bottom");
    model.component("comp2").physics("lshell").feature("dispi1").setIndex("Direction", "prescribed", 2);
    model.component("comp2").physics("lshell").create("dispi2", "DisplacementIntEP", 0);
    model.component("comp2").physics("lshell").feature("dispi2").selection().set(1);
    model.component("comp2").physics("lshell").feature("dispi2").set("applyTo", "bottom");
    model.component("comp2").physics("lshell").feature("dispi2").setIndex("Direction", "prescribed", 0);
    model.component("comp2").physics("lshell").create("ge1", "GlobalEquations", -1);
    model.component("comp2").physics("lshell").feature("ge1").setIndex("name", "forceL", 0, 0);
    model.component("comp2").physics("lshell").feature("ge1").setIndex("equation", "disp-u_IeL", 0, 0);
    model.component("comp2").physics("lshell").feature("ge1").set("CustomDependentVariableUnit", "1");
    model.component("comp2").physics("lshell").feature("ge1").set("DependentVariableQuantity", "force");
    model.component("comp2").physics("lshell").feature("ge1").set("SourceTermQuantity", "length");

    model.component("comp2").mesh("mesh2").create("map1", "Map");
    model.component("comp2").mesh("mesh2").feature("map1").selection().all();
    model.component("comp2").mesh("mesh2").feature("map1").create("dis1", "Distribution");
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis1").selection().set(1, 4, 7, 10);
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis1").set("numelem", 3);
    model.component("comp2").mesh("mesh2").feature("map1").create("dis2", "Distribution");
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis2").selection().set(2);
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis2").set("numelem", 10);
    model.component("comp2").mesh("mesh2").feature("map1").create("dis3", "Distribution");
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis3").selection().set(5);
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis3").set("numelem", 50);
    model.component("comp2").mesh("mesh2").feature("map1").create("dis4", "Distribution");
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis4").selection().set(8, 9);
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis4").set("type", "predefined");
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis4").set("elemcount", 20);
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis4").set("elemratio", 5);
    model.component("comp2").mesh("mesh2").run();

    model.study("std1").feature("stat").setSolveFor("/physics/lshell", false);
    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").feature("stat").setSolveFor("/physics/lshell", true);
    model.study("std2").label("\u7814\u7a76 [\u591a\u5c42\u58f3]");
    model.study("std2").feature("stat").set("geometricNonlinearity", true);
    model.study("std2").feature("stat").setSolveFor("/physics/solid", false);
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "lb", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "m", 0);
    model.study("std2").feature("stat").setIndex("pname", "lb", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "m", 0);
    model.study("std2").feature("stat").setIndex("pname", "disp", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "range(0,2e-4,8e-3)", 0);
    model.study("std2").feature("stat").set("usestol", true);
    model.study("std2").feature("stat").set("stol", "1e-4");

    return model;
  }

  public static Model run2(Model model) {
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("v1").feature("comp2_u2").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp2_u2").set("scaleval", "1e-3");
    model.sol("sol2").feature("v1").feature("comp2_ODE1").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp2_ODE1").set("scaleval", 200);
    model.sol("sol2").feature("s1").feature("p1").set("paramtuning", true);
    model.sol("sol2").feature("s1").feature("p1").set("pminstep", "1e-6");
    model.sol("sol2").feature("s1").feature("p1").set("porder", "linear");
    model.sol("sol2").feature("s1").feature("fc1").set("dtech", "const");

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("dset3lshelllshl", "LayeredMaterial");
    model.result().dataset("dset3lshelllshl").set("data", "dset3");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset3lshelllshl");
    model.result("pg5").setIndex("looplevel", 41, 0);
    model.result("pg5").label("\u5e94\u529b (lshell)");
    model.result("pg5").set("showlegends", true);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"lshell.misesGp"});
    model.result("pg5").feature("surf1").set("threshold", "manual");
    model.result("pg5").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg5").feature("surf1").set("colortable", "Rainbow");
    model.result("pg5").feature("surf1").set("colortabletrans", "none");
    model.result("pg5").feature("surf1").set("colorscalemode", "linear");
    model.result("pg5").feature("surf1").set("colortable", "Prism");
    model.result("pg5").feature("surf1").create("def", "Deform");
    model.result("pg5").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg5").feature("surf1").feature("def").set("scale", "1");
    model.result("pg5").feature("surf1").feature("def").set("expr", new String[]{"u2", "v2", "w2"});
    model.result("pg5").feature("surf1").feature("def")
         .set("descr", "\u4f4d\u79fb\u573a \uff08\u6750\u6599\u548c\u51e0\u4f55\u5750\u6807\u7cfb\uff09");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("surf1").set("unit", "MPa");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").run();
    model.result("pg6").label("\u754c\u9762\u5b8c\u597d\u60c5\u51b5 (lshell)");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").create("lss1", "LayeredMaterialSlice");
    model.result("pg6").feature("lss1").set("expr", "lshell.idmg");
    model.result("pg6").feature("lss1").set("locdef", "interfaces");
    model.result("pg6").feature("lss1").set("colortable", "Traffic");
    model.result("pg6").run();
    model.result("pg5").run();

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").set("type", "plotgroup");
    model.nodeGroup("grp2").placeAfter("plotgroup", "pg4");
    model.nodeGroup("grp2").add("plotgroup", "pg5");
    model.nodeGroup("grp2").add("plotgroup", "pg6");
    model.nodeGroup("grp2").label("\u5206\u5c42\u58f3\u7ed8\u56fe");
    model.nodeGroup("grp2").placeAfter(null);

    model.result("pg4").run();
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\u70b9\u4f4d\u79fb (m)");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u70b9\u8f7d\u8377 (N)");
    model.result("pg4").run();
    model.result("pg4").feature("glob1").set("titletype", "manual");
    model.result("pg4").feature("glob1").set("title", "\u8f7d\u8377-\u4f4d\u79fb\u66f2\u7ebf");
    model.result("pg4").feature("glob1").set("xdataunit", "m");
    model.result("pg4").feature("glob1").set("legend", true);
    model.result("pg4").feature("glob1").set("legendmethod", "manual");
    model.result("pg4").feature("glob1").setIndex("legends", "\u56fa\u4f53\u529b\u5b66", 0);
    model.result("pg4").feature().duplicate("glob2", "glob1");
    model.result("pg4").run();
    model.result("pg4").feature("glob2").set("data", "dset3");
    model.result("pg4").feature("glob2").setIndex("expr", "2*comp2.F_lpL", 0);
    model.result("pg4").feature("glob2").setIndex("unit", "", 0);
    model.result("pg4").feature("glob2").setIndex("descr", "\u8f7d\u8377", 0);
    model.result("pg4").feature("glob2").set("xdataexpr", "comp2.u_lpL");
    model.result("pg4").feature("glob2").set("titletype", "none");
    model.result("pg4").feature("glob2").setIndex("legends", "\u591a\u5c42\u58f3", 0);
    model.result("pg4").run();
    model.result().evaluationGroup().create("eg2", "EvaluationGroup");
    model.result().evaluationGroup("eg2").label("\u8f7d\u8377 (lshell)");
    model.result().evaluationGroup("eg2").set("data", "dset3");
    model.result().evaluationGroup("eg2").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("expr", "2*comp2.F_lpL", 0);
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("unit", "", 0);
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("descr", "Load", 0);
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("descr", "\u8f7d\u8377", 0);
    model.result().evaluationGroup("eg2").feature("gev1").set("dataseries", "maximum");
    model.result().evaluationGroup("eg2").run();
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
    model.result().export("anim1").label("\u52a8\u753b\uff1a\u5e94\u529b [\u591a\u5c42\u58f3]");
    model.result().export("anim1").set("plotgroup", "pg5");
    model.result().export("anim1").set("frametime", 0.3);
    model.result().export("anim1").showFrame();
    model.result().export("anim1").run();

    model.title("\u590d\u5408\u6750\u6599\u5c42\u5408\u677f\u7684\u6df7\u5408\u6a21\u5f0f\u8131\u5c42");

    model
         .description("\u590d\u5408\u6750\u6599\u4e2d\u7684\u754c\u9762\u7834\u574f\u6216\u8131\u5c42\u53ef\u4ee5\u4f7f\u7528\u201c\u5185\u805a\u529b\u6a21\u578b\u201d(CZM) \u8fdb\u884c\u6a21\u62df\u3002\u672c\u4f8b\u4ecb\u7ecd\u5982\u4f55\u5728\u591a\u5c42\u58f3\u4e2d\u901a\u8fc7\u53cc\u7ebf\u6027\u7275\u5f15-\u5206\u79bb\u5b9a\u5f8b\u5b9e\u73b0 CZM\uff0c\u7528\u4e8e\u9884\u6d4b\u6df7\u5408\u6a21\u5f0f\u8f6f\u5316\u5f00\u59cb\u548c\u8131\u5c42\u4f20\u64ad\uff0c\u5e76\u5c06\u7ed3\u679c\u4e0e\u57fa\u4e8e\u5b9e\u4f53\u5355\u5143\u7684\u6a21\u578b\u7ed3\u679c\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("mixed_mode_delamination.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
