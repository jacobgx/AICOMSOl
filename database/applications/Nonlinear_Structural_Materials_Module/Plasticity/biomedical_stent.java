/*
 * biomedical_stent.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:33 by COMSOL 6.3.0.290. */
public class biomedical_stent {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Nonlinear_Structural_Materials_Module\\Plasticity");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("type", "native");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "biomedical_stent.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("solid").feature("lemm1").set("geometricNonlinearity", "totalLagrangian");
    model.component("comp1").physics("solid").feature("lemm1").create("plsty1", "Plasticity", 3);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"193[GPa]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.27"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"7050"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic_material_model");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("sigmags", new String[]{"207[MPa]"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("Et", new String[]{"692[MPa]"});
    model.component("comp1").material("mat1").set("family", "steel");

    model.component("comp1").func().create("step1", "Step");
    model.component("comp1").func("step1").set("location", "2[mm]");
    model.component("comp1").func("step1").set("from", 1);
    model.component("comp1").func("step1").set("to", 0);
    model.component("comp1").func("step1").set("smooth", "1e-5");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("r", "sqrt(y^2+z^2)");
    model.component("comp1").variable("var1").descr("r", "\u4e0e x \u8f74\u7684\u5f84\u5411\u8ddd\u79bb");

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop1").selection().set(28);
    model.component("comp1").cpl("aveop1").set("frame", "material");

    model.component("comp1").func().create("pw1", "Piecewise");
    model.component("comp1").func("pw1").set("funcname", "r0");
    model.component("comp1").func("pw1").set("arg", "t");
    model.component("comp1").func("pw1").setIndex("pieces", 0, 0, 0);
    model.component("comp1").func("pw1").setIndex("pieces", 1, 0, 1);
    model.component("comp1").func("pw1").setIndex("pieces", "(2e-3-7.1e-4)*t+7.1e-4", 0, 2);
    model.component("comp1").func("pw1").setIndex("pieces", 1, 1, 0);
    model.component("comp1").func("pw1").setIndex("pieces", 2, 1, 1);
    model.component("comp1").func("pw1").setIndex("pieces", "(2e-3-7.1e-4)*(1-t)+2e-3", 1, 2);
    model.component("comp1").func("pw1").set("argunit", "s");
    model.component("comp1").func("pw1").set("fununit", "m");

    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym1").selection().set(5, 12, 18, 24, 30, 31);
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(4);
    model.component("comp1").physics("solid").feature("bndl1").set("forceType", "FollowerPressure");
    model.component("comp1").physics("solid").feature("bndl1").set("pressure", "p*step1(r)");
    model.component("comp1").physics("solid").create("ge1", "GlobalEquations", -1);
    model.component("comp1").physics("solid").feature("ge1").setIndex("name", "p", 0, 0);
    model.component("comp1").physics("solid").feature("ge1").setIndex("equation", "aveop1(r)-r0(t)", 0, 0);
    model.component("comp1").physics("solid").feature("ge1").setIndex("description", "\u538b\u529b", 0, 0);
    model.component("comp1").physics("solid").feature("ge1").set("DependentVariableQuantity", "pressure");
    model.component("comp1").physics("solid").feature("ge1").set("SourceTermQuantity", "length");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "4.5e-5");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmin", "4e-6");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hgradactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hgrad", 1.4);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hcurveactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hcurve", 0.3);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().set(57);
    model.component("comp1").cpl("intop1").set("opname", "central");
    model.component("comp1").cpl("intop1").set("frame", "material");
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop2").selection().set(3);
    model.component("comp1").cpl("intop2").set("opname", "distal");
    model.component("comp1").cpl("intop2").set("frame", "material");

    model.component("comp1").variable("var1").set("dogboning", "(distal(r)-central(r))/distal(r)");
    model.component("comp1").variable("var1").descr("dogboning", "\u77ac\u65f6\u975e\u5747\u5300\u81a8\u80c0");
    model.component("comp1").variable("var1").set("length", "2*abs(distal(x)-central(x))");
    model.component("comp1").variable("var1").descr("length", "\u53d8\u5f62\u652f\u67b6\u7684\u957f\u5ea6");
    model.component("comp1").variable("var1").set("L0", "2*abs(distal(X)-central(X))");
    model.component("comp1").variable("var1").descr("L0", "\u672a\u53d8\u5f62\u652f\u67b6\u7684\u957f\u5ea6");
    model.component("comp1").variable("var1").set("foreshortening", "(length-L0)/length");
    model.component("comp1").variable("var1").descr("foreshortening", "\u524d\u7f29");

    model.param().set("t", "0[s]");
    model.param().descr("t", "\u65f6\u95f4");

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "t", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "s", 0);
    model.study("std1").feature("stat").setIndex("pname", "t", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "s", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,1e-2,1.5)", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v1").feature("comp1_ODE1").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_ODE1").set("scaleval", "1e6");
    model.sol("sol1").feature("s1").feature("p1").create("st1", "StopCondition");
    model.sol("sol1").feature("s1").feature("p1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("s1").feature("p1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("s1").feature("p1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("s1").feature("p1").feature("st1")
         .setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("s1").feature("p1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("s1").feature("p1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("s1").feature("p1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("s1").feature("p1").feature("st1")
         .setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("s1").feature("p1").feature("st1").setIndex("stopcondarr", "comp1.p<0", 0);
    model.sol("sol1").feature("s1").feature("p1").feature("st1").set("storestopcondsol", "stepbefore");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 103, 0);
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
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 103, 0);
    model.result("pg2").label("\u7b49\u6548\u5851\u6027\u5e94\u53d8 (solid)");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"solid.epeGp"});
    model.result("pg2").feature("surf1").set("inheritplot", "none");
    model.result("pg2").feature("surf1").set("resolution", "normal");
    model.result("pg2").feature("surf1").set("colortabletype", "discrete");
    model.result("pg2").feature("surf1").set("bandcount", 11);
    model.result("pg2").feature("surf1").set("colortable", "AuroraAustralisDark");
    model.result("pg2").feature("surf1").set("descractive", true);
    model.result("pg2").feature("surf1").set("descr", "\u7b49\u6548\u5851\u6027\u5e94\u53d8");
    model.result("pg2").label("\u7b49\u6548\u5851\u6027\u5e94\u53d8 (solid)");
    model.result("pg2").run();
    model.result().dataset().create("mir1", "Mirror3D");
    model.result().dataset().create("mir2", "Mirror3D");
    model.result().dataset("mir2").set("data", "mir1");
    model.result().dataset("mir2").set("quickplane", "zx");
    model.result().dataset().create("sec1", "Sector3D");
    model.result().dataset("sec1").set("data", "mir2");
    model.result().dataset("sec1").setIndex("genpoints", 1, 1, 0);
    model.result().dataset("sec1").setIndex("genpoints", 0, 1, 2);
    model.result().dataset("sec1").set("sectors", 6);
    model.result("pg1").run();
    model.result("pg1").set("data", "sec1");
    model.result("pg1").set("edges", false);
    model.result("pg1").run();
    model.result("pg1").feature("vol1").set("unit", "MPa");
    model.result("pg1").run();
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("titletype", "none");
    model.result("pg1").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg1").run();
    model.result("pg1").run();

    model.view("view2").set("showgrid", false);

    model.result("pg1").run();
    model.result("pg1").stepPrevious(0);
    model.result("pg1").run();
    model.result("pg1").stepPrevious(0);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").set("data", "sec1");
    model.result("pg2").set("edges", false);
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("surf2", "surf1");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("surf1").create("def1", "Deform");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg2").feature("surf1").feature("def1").set("scale", 1);
    model.result("pg2").run();
    model.result("pg2").feature("surf2").set("titletype", "none");
    model.result("pg2").feature("surf2").create("mtrl1", "MaterialAppearance");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u77ac\u65f6\u975e\u5747\u5300\u81a8\u80c0\u548c\u524d\u7f29");
    model.result("pg3").setIndex("looplevelinput", "manual", 0);
    model.result("pg3")
         .setIndex("looplevel", new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101}, 0);
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").set("expr", new String[]{"dogboning"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"\u77ac\u65f6\u975e\u5747\u5300\u81a8\u80c0"});
    model.result("pg3").feature("glob1").set("unit", new String[]{"1"});
    model.result("pg3").feature("glob1").set("expr", new String[]{"dogboning", "foreshortening"});
    model.result("pg3").feature("glob1")
         .set("descr", new String[]{"\u77ac\u65f6\u975e\u5747\u5300\u81a8\u80c0", "\u524d\u7f29"});
    model.result("pg3").feature("glob1").set("xdataexpr", "p");
    model.result("pg3").feature("glob1").set("xdatadescr", "\u538b\u529b");
    model.result("pg3").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u53cd\u51b2\u529b\u8ba1\u7b97");
    model.result().evaluationGroup("eg1").setIndex("looplevelinput", "manual", 0);
    model.result().evaluationGroup("eg1").setIndex("looplevel", new int[]{101}, 0);
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "(length-with(103,length))/length", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "\u7eb5\u5411\u53cd\u51b2\u529b", 0);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("expr", "(distal(r)-with(103,distal(r)))/distal(r)", 1);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "\u8fdc\u7aef\u5f84\u5411\u53cd\u51b2\u529b", 1);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("expr", "(central(r)-with(103,central(r)))/central(r)", 2);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "\u4e2d\u5fc3\u5f84\u5411\u53cd\u51b2\u529b", 2);
    model.result().evaluationGroup("eg1").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("\u5b8c\u6574\u7684\u51e0\u4f55\u4e0e\u7f51\u683c");
    model.result("pg4").set("titletype", "none");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("data", "sec1");
    model.result("pg4").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").create("mesh1", "Mesh");
    model.result("pg4").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg4").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg4").feature("mesh1").set("elemcolor", "none");
    model.result("pg4").run();
    model.result("pg1").run();

    model.title("\u751f\u7269\u533b\u7528\u652f\u67b6\u6269\u5f20\u8fc7\u7a0b\u4e2d\u7684\u5851\u6027\u53d8\u5f62");

    model
         .description("\u652f\u67b6\u662f\u4e00\u79cd\u91d1\u5c5e\u4e1d\u7f51\u7ba1\uff0c\u7528\u4e8e\u5728\u8840\u7ba1\u6210\u5f62\u672f\uff08\u53bb\u9664\u6216\u538b\u7f29\u6591\u5757\uff09\u8fc7\u7a0b\u4e2d\u6491\u5f00\u51a0\u72b6\u52a8\u8109\uff0c\u6269\u5f20\u7684\u652f\u67b6\u5145\u5f53\u4fdd\u6301\u8840\u7ba1\u6491\u5f00\u7684\u652f\u67b6\u3002\u5728\u8fd9\u4e2a\u8fc7\u7a0b\u4e2d\uff0c\u652f\u67b6\u7684\u4e0d\u5747\u5300\u6269\u5f20\u53ca\u5176\u6536\u7f29\u90fd\u4f1a\u5bf9\u52a8\u8109\u9020\u6210\u635f\u4f24\u3002\u672c\u4f8b\u4f7f\u7528\u5177\u6709\u7ebf\u6027\u786c\u5316\u7684\u5f39\u5851\u6027\u6750\u6599\u6a21\u578b\u6765\u7814\u7a76\u652f\u67b6\u5728\u627f\u53d7\u5f84\u5411\u5411\u5916\u538b\u529b\u65f6\u7684\u53d8\u5f62\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("biomedical_stent.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
