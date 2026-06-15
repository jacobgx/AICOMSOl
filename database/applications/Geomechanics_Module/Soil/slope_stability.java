/*
 * slope_stability.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:14 by COMSOL 6.3.0.290. */
public class slope_stability {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Geomechanics_Module\\Soil");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("dl", "PorousMediaFlowDarcy", "geom1");
    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/dl", true);
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("L1", "24[m]", "\u5824\u575d\u957f\u5ea6\uff08\u5de6\uff09");
    model.param().set("L2", "5[m]", "\u5824\u575d\u957f\u5ea6\uff08\u9876\uff09");
    model.param().set("L3", "24[m]", "\u5824\u575d\u957f\u5ea6\uff08\u53f3\uff09");
    model.param().set("L4", "12[m]", "\u575d\u9ad8");
    model.param().set("Hw", "10[m]", "\u6c34\u4f4d");
    model.param().set("Hs", "4[m]", "\u53ef\u80fd\u7684\u6e17\u6d41\u4f4d");
    model.param().set("E_soil", "100[MPa]", "\u6768\u6c0f\u6a21\u91cf");
    model.param().set("nu_soil", "0.4", "\u6cca\u677e\u6bd4");
    model.param().set("rho_soil", "2000[kg/m^3]", "\u571f\u58e4\u5bc6\u5ea6");
    model.param().set("rho_wat", "1000[kg/m^3]", "\u6c34\u5bc6\u5ea6");
    model.param().set("psi", "0.3[1]", "\u5b54\u9699\u7387");
    model.param().set("c", "25[kPa]", "\u5185\u805a\u529b");
    model.param().set("phi_sat", "30[deg]", "\u9971\u548c\u571f\u58e4\u7684\u6469\u64e6\u89d2");
    model.param().set("phi_un", "20[deg]", "\u975e\u9971\u548c\u571f\u58e4\u7684\u6469\u64e6\u89d2");
    model.param().set("FOS", "1[1]", "\u5b89\u5168\u7cfb\u6570");

    model.func().create("int1", "Interpolation");
    model.func("int1")
         .set("table", new String[][]{{"0", "5e-4"}, 
         {"-0.1", "5e-4"}, 
         {"-0.6", "3e-4"}, 
         {"-2", "6.5e-5"}, 
         {"-3", "6.1e-6"}, 
         {"-4", "1e-6"}, 
         {"-5", "2e-7"}, 
         {"-8", "7.5e-8"}, 
         {"-10", "7.5e-8"}});
    model.func("int1").set("funcname", "cond");
    model.func("int1").setIndex("argunit", "m", 0);
    model.func("int1").setIndex("fununit", "m/s", 0);

    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "L1", 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "L4", 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "L1+L2", 2, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "L4", 2, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "L1+L2+L3", 3, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 3, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "L1+L2+L3*2", 4, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 4, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "L1+L2+L3*2", 5, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-L4*2", 5, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-L1", 6, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-L4*2", 6, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-L1", 7, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 7, 1);
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").selection("pointinsketch").set("pol1", 4);
    model.component("comp1").geom("geom1").feature("fil1").set("radius", 5);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "Hw*L1/L4", 0);
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "Hw", 1);
    model.component("comp1").geom("geom1").feature().duplicate("pt2", "pt1");
    model.component("comp1").geom("geom1").feature("pt2").setIndex("p", "L1+L2+L3-Hs*L1/L4", 0);
    model.component("comp1").geom("geom1").feature("pt2").setIndex("p", "Hs", 1);
    model.component("comp1").geom("geom1").run("pt2");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("Saturated", "dl.Hp>=0");
    model.component("comp1").variable("var1")
         .descr("Saturated", "\u9971\u548c\u533a\u57df\u7684\u5e03\u5c14\u53d8\u91cf");
    model.component("comp1").variable("var1").set("Unsaturated", "dl.Hp<0");
    model.component("comp1").variable("var1")
         .descr("Unsaturated", "\u975e\u9971\u548c\u533a\u57df\u7684\u5e03\u5c14\u53d8\u91cf");
    model.component("comp1").variable("var1").set("K", "cond(dl.Hp)");
    model.component("comp1").variable("var1").descr("K", "\u6c34\u529b\u4f20\u5bfc\u7387");
    model.component("comp1").variable("var1").set("C", "c/FOS");
    model.component("comp1").variable("var1").descr("C", "\u53c2\u6570\u5316\u7684\u5185\u805a\u529b");
    model.component("comp1").variable("var1")
         .set("PHI", "atan(tan(phi_un)/FOS)*Unsaturated+atan(tan(phi_sat)/FOS)*Saturated");
    model.component("comp1").variable("var1").descr("PHI", "\u53c2\u6570\u5316\u7684\u6469\u64e6\u89d2");

    model.component("comp1").cpl().create("maxop1", "Maximum");
    model.component("comp1").cpl("maxop1").selection().set(1);

    model.material().create("mat1", "Common", "");
    model.material("mat1").label("\u571f\u58e4");
    model.material().create("mat2", "Common", "");
    model.material("mat2").label("\u6c34");
    model.component("comp1").material().create("pmat1", "PorousMedia");
    model.component("comp1").material("pmat1").set("linkBase", "mat1");
    model.component("comp1").material("pmat1").feature().create("fluid1", "Fluid", "comp1");
    model.component("comp1").material("pmat1").feature("fluid1").set("link", "mat2");
    model.component("comp1").material("pmat1").feature().create("solid1", "Solid", "comp1");
    model.component("comp1").material("pmat1").feature("solid1").set("vfrac", "1-psi");

    model.component("comp1").physics("dl").feature("porous1").feature("pm1")
         .set("permeabilityModelType", "conductivity");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1")
         .set("K", new String[]{"K", "0", "0", "0", "K", "0", "0", "0", "K"});

    model.material("mat1").propertyGroup("def").set("density", new String[]{"rho_soil"});
    model.material("mat1").propertyGroup().create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.material("mat1").propertyGroup("Enu").set("E", new String[]{"E_soil"});
    model.material("mat1").propertyGroup("Enu").set("nu", new String[]{"nu_soil"});
    model.material("mat2").propertyGroup("def").set("density", new String[]{"rho_wat"});

    model.component("comp1").physics("dl").create("ph1", "PressureHead", 1);
    model.component("comp1").physics("dl").feature("ph1").selection().set(1, 3, 4);
    model.component("comp1").physics("dl").feature("ph1").set("Hp0", "Hw-y");
    model.component("comp1").physics("dl").create("ph2", "PressureHead", 1);
    model.component("comp1").physics("dl").feature("ph2").selection().set(8, 9, 11);
    model.component("comp1").physics("dl").create("ph3", "PressureHead", 1);
    model.component("comp1").physics("dl").feature("ph3").selection().set(10);
    model.component("comp1").physics("dl").feature("ph3").set("Hp0", "-y");
    model.component("comp1").physics("dl").prop("GravityEffects").set("IncludeGravity", true);
    model.component("comp1").physics("dl").feature("gr1").set("GravityType", "Elevation");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 4);
    model.component("comp1").mesh("mesh1").create("se1", "SizeExpression");
    model.component("comp1").mesh("mesh1").feature().move("se1", 1);
    model.component("comp1").mesh("mesh1").feature("se1")
         .set("sizeexpr", "if(Y>-L4&&X>L1/2&&X<(L1+L2+L3*1.5),0.75,10)");
    model.component("comp1").mesh("mesh1").feature("se1").set("numcell", 50);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1: \u8fbe\u897f\u5b9a\u5f8b");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("stat").setSolveFor("/physics/solid", false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").run();
    model.result("pg1").label("\u538b\u529b\u6c34\u5934");
    model.result("pg1").create("con1", "Contour");
    model.result("pg1").feature("con1").set("expr", "dl.Hp");
    model.result("pg1").feature("con1").set("descr", "\u538b\u529b\u6c34\u5934");
    model.result("pg1").feature("con1").set("levelmethod", "levels");
    model.result("pg1").feature("con1").set("levels", "range(0,3,30)");
    model.result("pg1").feature("con1").set("contourtype", "tubes");
    model.result("pg1").feature("con1").set("tuberadiusscaleactive", true);
    model.result("pg1").feature("con1").set("tuberadiusscale", 0.1);
    model.result("pg1").run();

    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 1);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(3, 4);
    model.component("comp1").physics("solid").feature("bndl1").set("forceType", "FollowerPressure");
    model.component("comp1").physics("solid").feature("bndl1").set("pressure", "p");
    model.component("comp1").physics("solid").create("fix1", "Fixed", 1);
    model.component("comp1").physics("solid").feature("fix1").selection().set(2);
    model.component("comp1").physics("solid").create("roll1", "Roller", 1);
    model.component("comp1").physics("solid").feature("roll1").selection().set(1, 10);
    model.component("comp1").physics("solid").create("gacc1", "GravityAcceleration", -1);
    model.component("comp1").physics("solid").feature("lemm1").set("reducedIntegration", true);
    model.component("comp1").physics("solid").feature("lemm1").create("exs1", "ExternalStress", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("exs1")
         .set("StressInputType", "PorePressure");
    model.component("comp1").physics("solid").feature("lemm1").feature("exs1").set("pA", "p*Saturated");
    model.component("comp1").physics("solid").feature("lemm1").feature("exs1").set("alphaB_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").feature("exs1").set("alphaB", 1);
    model.component("comp1").physics("solid").feature("lemm1").feature("exs1").set("pref", 0);

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/dl", true);
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2")
         .label("\u7814\u7a76 2: \u56fa\u4f53\u529b\u5b66\uff08\u539f\u4f4d\u5e94\u529b\u521d\u59cb\u5316\uff09");
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.component("comp1").physics("solid").feature("lemm1").create("sopl1", "SoilPlasticity", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("sopl1")
         .set("failureCriterion", "MohrCoulomb");
    model.component("comp1").physics("solid").feature("lemm1").feature("sopl1")
         .set("nonlocalPlasticModel", "impGradient");
    model.component("comp1").physics("solid").feature("lemm1").feature("sopl1").set("lint", 0.1);
    model.component("comp1").physics("solid").feature("lemm1").create("iss1", "InitialStressandStrain", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("iss1")
         .set("Sil", new String[]{"withsol('sol2',solid.sx)", "withsol('sol2',solid.sxy)", "withsol('sol2',solid.sxz)", "withsol('sol2',solid.sxy)", "withsol('sol2',solid.sy)", "withsol('sol2',solid.syz)", "withsol('sol2',solid.sxz)", "withsol('sol2',solid.syz)", "withsol('sol2',solid.sz)"});

    model.material("mat1").propertyGroup().create("Soil", "Soil", "Soil_material");
    model.material("mat1").propertyGroup("Soil").set("cohesion0", new String[]{"comp1.C"});
    model.material("mat1").propertyGroup("Soil").set("phis", new String[]{"comp1.PHI"});

    model.study("std2").feature("stat").set("useadvanceddisable", true);
    model.study("std2").feature("stat").setSolveFor("/physics/dl", false);
    model.study("std2").feature("stat")
         .set("disabledphysics", new String[]{"solid/lemm1/sopl1", "solid/lemm1/iss1"});
    model.study("std2").feature("stat").set("usesol", true);
    model.study("std2").feature("stat").set("notsolmethod", "sol");
    model.study("std2").feature("stat").set("notstudy", "std1");
    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/dl", true);
    model.study("std3").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std3").label("\u7814\u7a76 3: \u56fa\u4f53\u529b\u5b66\uff08\u5b89\u5168\u7cfb\u6570\uff09");
    model.study("std3").setGenPlots(false);
    model.study("std3").feature("stat").setSolveFor("/physics/dl", false);
    model.study("std3").feature("stat").set("usesol", true);
    model.study("std3").feature("stat").set("notsolmethod", "sol");
    model.study("std3").feature("stat").set("notstudy", "std1");
    model.study("std3").feature("stat").set("useparam", true);
    model.study("std3").feature("stat").setIndex("pname", "L1", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "m", 0);
    model.study("std3").feature("stat").setIndex("pname", "L1", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "m", 0);
    model.study("std3").feature("stat").setIndex("pname", "FOS", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "range(1,0.02,1.92)", 0);
    model.study("std3").feature("stat").setIndex("punit", "", 0);
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").label("\u6ed1\u79fb\u9762");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").set("titletype", "label");
    model.result("pg2").create("con1", "Contour");
    model.result("pg2").feature("con1").set("expr", "solid.disp");
    model.result("pg2").feature("con1").set("descr", "\u4f4d\u79fb\u5927\u5c0f");
    model.result("pg2").feature("con1").set("levelmethod", "levels");
    model.result("pg2").feature("con1").set("levels", "0 0.1 Inf");
    model.result("pg2").feature("con1").set("contourtype", "filled");
    model.result("pg2").feature("con1").set("colortable", "GrayPrint");
    model.result("pg2").feature("con1").set("colortabletrans", "reverse");
    model.result("pg2").feature("con1").set("colorlegend", false);
    model.result("pg2").run();
    model.result("pg2").create("arws1", "ArrowSurface");
    model.result("pg2").feature("arws1").set("expr", new String[]{"u", "v"});
    model.result("pg2").feature("arws1").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").label("\u7b49\u6548\u5851\u6027\u5e94\u53d8");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "solid.epeGp");
    model.result("pg3").feature("surf1").set("descr", "\u7b49\u6548\u5851\u6027\u5e94\u53d8");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u5b89\u5168\u7cfb\u6570");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "maxop1(solid.disp)", 0);
    model.result("pg4").feature("glob1").setIndex("unit", "mm", 0);
    model.result("pg4").feature("glob1").setIndex("descr", "\u6700\u5927\u4f4d\u79fb", 0);
    model.result("pg4").feature("glob1").set("xdata", "expr");
    model.result("pg4").feature("glob1").set("xdataexpr", "FOS");
    model.result("pg4").feature("glob1").set("xdatadescr", "\u5b89\u5168\u7cfb\u6570");
    model.result("pg4").feature("glob1").set("linemarker", "circle");
    model.result("pg4").feature("glob1").set("legend", false);
    model.result("pg4").run();
    model.result("pg4").set("legendpos", "upperleft");
    model.result("pg4").run();
    model.result().dataset().create("extr1", "Extrude2D");
    model.result().dataset("extr1").set("data", "dset3");
    model.result().dataset("extr1").set("zmax", "L1+L2+L3");
    model.result().dataset("extr1").set("zvar", "Z");
    model.result().dataset("extr1").set("planemap", "xz");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").label("\u4f4d\u79fb");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "solid.disp");
    model.result("pg5").feature("surf1").set("descr", "\u4f4d\u79fb\u5927\u5c0f");
    model.result("pg5").feature("surf1").set("unit", "mm");
    model.result("pg5").run();
    model.result("pg5").run();

    model.title("\u5824\u575d\u8fb9\u5761\u7a33\u5b9a\u6027");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u5bf9\u5305\u542b\u5b54\u9699\u538b\u529b\u7684\u5824\u575d\u8fdb\u884c\u8fb9\u5761\u7a33\u5b9a\u6027\u5206\u6790\uff0c\u901a\u8fc7\u8fbe\u897f\u5b9a\u5f8b\u63cf\u8ff0\u591a\u5b54\u571f\u58e4\u4e2d\u7684\u6d41\u4f53\u8fd0\u79fb\uff0c\u5e76\u4f7f\u7528\u83ab\u5c14-\u5e93\u4ed1\u6a21\u578b\u6267\u884c\u5f39\u5851\u6027\u5206\u6790\u3002\u5176\u4e2d\u91c7\u7528\u526a\u5207\u6298\u51cf\u6280\u672f\uff0c\u6839\u636e\u5b89\u5168\u7cfb\u6570\u5c06\u6750\u6599\u5c5e\u6027\u53c2\u6570\u5316\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("slope_stability.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
