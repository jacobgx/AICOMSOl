/*
 * cohesive_zone_debonding.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:02 by COMSOL 6.3.0.290. */
public class cohesive_zone_debonding {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Contact_and_Friction");

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
    model.param().set("lb", "102[mm]", "\u957f\u5ea6");
    model.param().set("wb", "25.4[mm]", "\u5bbd\u5ea6");
    model.param().set("hb", "2*1.56[mm]", "\u539a\u5ea6");
    model.param().set("cl", "34.1[mm]", "\u521d\u59cb\u88c2\u7eb9\u957f\u5ea6");
    model.param().set("pn", "1e6[N/mm^3]", "\u7f5a\u521a\u5ea6");
    model.param().set("sigmat", "80[MPa]", "\u6cd5\u5411\u6297\u62c9\u5f3a\u5ea6");
    model.param().set("sigmas", "100[MPa]", "\u526a\u5207\u5f3a\u5ea6");
    model.param().set("Gct", "0.969[kJ/m^2]", "\u4e34\u754c\u80fd\u91cf\u91ca\u653e\u7387\uff0c\u62c9\u4f38");
    model.param().set("Gcs", "1.719[kJ/m^2]", "\u4e34\u754c\u80fd\u91cf\u91ca\u653e\u7387\uff0c\u526a\u5207");
    model.param().set("alpha", "2.284", "Benzeggagh \u548c Kenane (B-K) \u51c6\u5219\u7684\u6307\u6570");
    model.param().set("disp", "0", "\u4f4d\u79fb\u53c2\u6570");
    model.param().set("mm", "0.5", "\u6a21\u5f0f\u6df7\u5408\u6bd4");
    model.param().set("ll", "lb/2*(0.5*sqrt(3*(1-mm)/mm)+1)/(3-0.5*sqrt(3*(1-mm)/mm))", "\u6746\u957f\u5ea6");
    model.param()
         .set("lr", "8*((6*mm+sqrt(3*mm*(1-mm)))/(3+9*mm+8*sqrt(3*mm*(1-mm))))", "\u4e2d\u95f4/\u5f00\u88c2\u8fb9\u7684\u8f7d\u8377\u6bd4");

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

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("u_Ie", "intop1(w)", "\u8fb9\u7f18\u5904\u4f4d\u79fb");
    model.component("comp1").variable("var1").set("w_c", "intop2(w)", "\u4e2d\u5fc3\u4f4d\u79fb");
    model.component("comp1").variable("var1")
         .set("u_lp", "(3*ll-lb/2)/4/(lb/2)*u_Ie+((ll+lb/2)/(lb/2))*(-w_c+u_Ie/4)", "\u8f7d\u8377\u70b9\u4f4d\u79fb");
    model.component("comp1").variable("var1").set("F_lp", "force*lb/2/ll", "\u8f7d\u8377\u70b9\u529b");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().set(3);
    model.component("comp1").cpl("intop1").set("frame", "material");
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop2").selection().set(15);
    model.component("comp1").cpl("intop2").set("frame", "material");

    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup()
         .create("TransverseIsotropic", "TransverseIsotropic", "Transversely isotropic");
    model.material("mat1")
         .label("Unidirectional fiber lamina: AS4/APC2 carbon/PEEK thermoplastic [fiber volume fraction 50%]");
    model.material("mat1").propertyGroup("def").label("Basic");
    model.material("mat1").propertyGroup("def").set("density", "1570[kg/m^3]");
    model.material("mat1").propertyGroup("def")
         .setPropertyInfo("density", "Reference: P.P. Camanho, C.G. Davila, and M.F. De Moura, Numerical Simulation of Mixed Mode Progressive Delamination in Composite Materials, Journal of composite materials, vol. 37, no. 16, 2003.");
    model.material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"-0.2E-6[1/K]", "0", "0", "0", "24E-6[1/K]", "0", "0", "0", "24E-6[1/K]"});
    model.material("mat1").propertyGroup("TransverseIsotropic").label("Transversely isotropic");
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
    model.component("comp1").physics("solid").feature("el1").selection().set(7);
    model.component("comp1").physics("solid").feature("el1").set("forceType", "TotalForce");
    model.component("comp1").physics("solid").feature("el1").set("force", new String[]{"0", "0", "force"});
    model.component("comp1").physics("solid").create("el2", "EdgeLoad", 1);
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
    model.result("pg1").label("\u5e94\u529b (solid)");
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
    model.result("pg1").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");

    model.sol("sol1").runFromTo("st1", "v1");

    model.result().remove("pg1");

    model.study("std1").feature("stat").set("plotgroup", "Default");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").label("\u5e94\u529b (solid)");
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
    model.result("pg1").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
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
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "solid.bdmg");
    model.result("pg2").feature("surf1").set("descr", "\u635f\u4f24");
    model.result("pg2").feature("surf1").set("colortable", "Traffic");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
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
    model.result("pg4").set("titletype", "manual");
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
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "2*F_lp", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "Load", 0);
    model.result().evaluationGroup("eg1").feature("gev1").set("dataseries", "maximum");
    model.result().evaluationGroup("eg1").run();
    model.result("pg1").run();

    model.title("\u5c42\u538b\u590d\u5408\u6750\u6599\u7684\u6df7\u5408\u6a21\u5f0f\u5265\u79bb");

    model
         .description("\u7531\u4e8e\u8131\u5c42\u6216\u5265\u79bb\u800c\u4ea7\u751f\u7684\u754c\u9762\u7834\u574f\u53ef\u4ee5\u901a\u8fc7\u5185\u805a\u529b\u6a21\u578b (CZM) \u6765\u6a21\u62df\u3002\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u5b9e\u73b0\u9075\u4ece\u53cc\u7ebf\u6027\u62c9\u4f38\u5206\u79bb\u5b9a\u5f8b\u7684 CZM\u3002\u9884\u6d4b\u4e86\u6df7\u5408\u6a21\u5f0f\u8f6f\u5316\u8d77\u70b9\u548c\u590d\u5408\u6750\u6599\u5265\u79bb\u7684\u53d1\u5c55\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("cohesive_zone_debonding.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
