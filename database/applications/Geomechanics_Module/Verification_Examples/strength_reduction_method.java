/*
 * strength_reduction_method.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:16 by COMSOL 6.3.0.290. */
public class strength_reduction_method {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Geomechanics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.param().label("\u51e0\u4f55\u548c\u6c42\u89e3\u5668\u53c2\u6570");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("L1", "85[m]", "\u957f\u5ea6\u53c2\u6570");
    model.param().set("L2", "20[m]", "\u957f\u5ea6\u53c2\u6570");
    model.param().set("Lslope", "H2/tan(alpha)", "\u957f\u5ea6\u53c2\u6570");
    model.param().set("H1", "20[m]", "\u9ad8\u5ea6\u53c2\u6570");
    model.param().set("H2", "10[m]", "\u9ad8\u5ea6\u53c2\u6570");
    model.param().set("alpha", "15[deg]", "\u5761\u89d2");
    model.param().set("FOS", "1[1]", "\u5b89\u5168\u7cfb\u6570");
    model.param().create("par2");
    model.param("par2").label("\u6750\u6599\u53c2\u6570");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("E_soil", "20[MPa]", "\u6768\u6c0f\u6a21\u91cf");
    model.param("par2").set("nu_soil", "0.3", "\u6cca\u677e\u6bd4");
    model.param("par2").set("rho_soil", "19[kN/m^3]/g_const", "\u571f\u58e4\u5bc6\u5ea6");
    model.param("par2").set("c", "20[kPa]", "\u5185\u805a\u529b");
    model.param("par2").set("phi", "25[deg]", "\u6469\u64e6\u89d2");
    model.param("par2").set("psi", "0[deg]", "\u81a8\u80c0\u89d2");
    model.param("par2").paramCase().create("case1");
    model.param("par2").paramCase().create("case2");
    model.param("par2").paramCase("case2").set("psi", "25[deg]");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1")
         .set("beta_f", "cos(atan(tan(phi)/FOS))*cos(atan(tan(psi)/FOS))/(1-sin(atan(tan(phi)/FOS))*sin(atan(tan(psi)/FOS)))");
    model.component("comp1").variable("var1").descr("beta_f", "\u6298\u51cf\u56e0\u5b50");
    model.component("comp1").variable("var1").set("c_r", "beta_f*c");
    model.component("comp1").variable("var1").descr("c_r", "\u6298\u51cf\u5185\u805a\u529b");
    model.component("comp1").variable("var1").set("phi_r", "atan(beta_f*tan(phi))");
    model.component("comp1").variable("var1").descr("phi_r", "\u6298\u51cf\u6469\u64e6\u89d2");
    model.component("comp1").variable("var1").set("c_p", "c_r/FOS");
    model.component("comp1").variable("var1").descr("c_p", "\u53c2\u6570\u5316\u5185\u805a\u529b");
    model.component("comp1").variable("var1").set("phi_p", "atan(tan(phi_r)/FOS)");
    model.component("comp1").variable("var1").descr("phi_p", "\u53c2\u6570\u5316\u6469\u64e6\u89d2");

    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol1").set("x", "0, L1,L1,L2+Lslope,L2,0");
    model.component("comp1").geom("geom1").feature("pol1").set("y", "0, 0, H1+H2,H1+H2,H1,H1");
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("solid").feature("lemm1").set("reducedIntegration", true);
    model.component("comp1").physics("solid").feature("lemm1").create("sopl1", "SoilPlasticity", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("sopl1")
         .set("failureCriterion", "MohrCoulomb");
    model.component("comp1").physics("solid").feature("lemm1").feature("sopl1")
         .set("nonlocalPlasticModel", "impGradient");
    model.component("comp1").physics("solid").feature("lemm1").feature("sopl1").set("lint", 0.1);
    model.component("comp1").physics("solid").feature("lemm1").create("iss1", "InitialStressandStrain", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("iss1")
         .set("Sil", new String[]{"withsol('sol2',solid.sxx)", "withsol('sol2',solid.sxy)", "withsol('sol2',solid.sxz)", "withsol('sol2',solid.sxy)", "withsol('sol2',solid.syy)", "withsol('sol2',solid.syz)", "withsol('sol2',solid.sxz)", "withsol('sol2',solid.syz)", "withsol('sol2',solid.szz)"});
    model.component("comp1").physics("solid").create("gacc1", "GravityAcceleration", -1);
    model.component("comp1").physics("solid").create("roll1", "Roller", 1);
    model.component("comp1").physics("solid").feature("roll1").selection().set(1, 6);
    model.component("comp1").physics("solid").create("fix1", "Fixed", 1);
    model.component("comp1").physics("solid").feature("fix1").selection().set(2);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u571f\u58e4\u6750\u6599");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"E_soil"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"nu_soil"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rho_soil"});
    model.component("comp1").material("mat1").propertyGroup().create("Soil", "Soil", "Soil_material");
    model.component("comp1").material("mat1").propertyGroup("Soil").set("cohesion0", new String[]{"c_p"});
    model.component("comp1").material("mat1").propertyGroup("Soil").set("phis", new String[]{"phi_p"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmax", 0.75);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat")
         .set("disabledphysics", new String[]{"solid/lemm1/sopl1", "solid/lemm1/iss1"});
    model.study("std1").create("stat2", "Stationary");
    model.study("std1").feature("stat2").set("useinitsol", true);
    model.study("std1").feature("stat2").set("useparam", true);
    model.study("std1").feature("stat2").setIndex("pname", "alpha", 0);
    model.study("std1").feature("stat2").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat2").setIndex("punit", "rad", 0);
    model.study("std1").feature("stat2").setIndex("pname", "alpha", 0);
    model.study("std1").feature("stat2").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat2").setIndex("punit", "rad", 0);
    model.study("std1").feature("stat2").setIndex("pname", "FOS", 0);
    model.study("std1").feature("stat2").setIndex("plistarr", "1 4", 0);
    model.study("std1").feature("stat2").setIndex("punit", "", 0);
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "alpha", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "rad", 0);
    model.study("std1").feature("param").setIndex("pname", "alpha", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "rad", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(15,10,45)", 0);
    model.study("std1").feature("param").setIndex("punit", "deg", 0);
    model.study("std1").create("param2", "Parametric");
    model.study("std1").feature("param2").set("sweeptype", "switch");
    model.study("std1").feature("param2").setIndex("switchname", "default", 0);
    model.study("std1").feature("param2").setIndex("switchcase", "all", 0);
    model.study("std1").feature("param2").setIndex("switchlistarr", "", 0);
    model.study("std1").feature("param2").setIndex("switchname", "default", 0);
    model.study("std1").feature("param2").setIndex("switchcase", "all", 0);
    model.study("std1").feature("param2").setIndex("switchlistarr", "", 0);
    model.study("std1").feature("param2").setIndex("switchname", "par2", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s2").feature("p1").set("ponerror", "skip");
    model.sol("sol1").feature("s2").feature("p1").set("paramtuning", true);
    model.sol("sol1").feature("s2").feature("p1").set("pinitstep", 0.2);
    model.sol("sol1").feature("s2").feature("p1").set("pmaxstep", 0.2);
    model.sol("sol1").feature("s2").feature("p1").set("porder", "constant");
    model.sol("sol1").feature("s2").feature("p1").set("pout", "psteps");
    model.sol("sol1").feature("s2").feature("p1").create("st1", "StopCondition");

    model.component("comp1").cpl().create("maxop1", "Maximum");
    model.component("comp1").cpl("maxop1").selection().set(1);

    model.sol("sol1").feature("s2").feature("p1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("s2").feature("p1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("s2").feature("p1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("s2").feature("p1").feature("st1")
         .setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("s2").feature("p1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("s2").feature("p1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("s2").feature("p1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("s2").feature("p1").feature("st1")
         .setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("s2").feature("p1").feature("st1")
         .setIndex("stopcondarr", "comp1.maxop1(comp1.solid.disp)>Lslope/20", 0);
    model.sol("sol1").feature("s2").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("s2").feature("fc1").set("maxiter", 8);

    model.study("std1").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std1");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol3");
    model.batch("p2").run("compute");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset3");
    model.result("pg1").setIndex("looplevel", 8, 0);
    model.result("pg1").setIndex("looplevel", 4, 1);
    model.result("pg1").setIndex("looplevel", 2, 2);
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("resolution", "normal");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").set("includeparameters", false);
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").set("data", "dset3");
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("looplevelinput", "manual", 2);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("looplevel", new int[]{1}, 2);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "alpha", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("unit", "deg", 0);
    model.result().evaluationGroup("eg1").feature().duplicate("gev2", "gev1");
    model.result().evaluationGroup("eg1").feature("gev2").setIndex("expr", "FOS", 0);
    model.result().evaluationGroup("eg1").feature("gev2").setIndex("unit", 1, 0);
    model.result().evaluationGroup("eg1").feature().duplicate("gev3", "gev2");
    model.result().evaluationGroup("eg1").feature("gev3").setIndex("looplevel", new int[]{2}, 2);
    model.result().evaluationGroup("eg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("FOS vs. \u5761\u89d2");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u5b89\u5168\u7cfb\u6570 (1)");
    model.result("pg2").create("tblp1", "Table");
    model.result("pg2").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg2").feature("tblp1").set("linewidth", "preference");
    model.result("pg2").feature("tblp1").set("source", "evaluationgroup");
    model.result("pg2").feature("tblp1").set("linemarker", "circle");
    model.result("pg2").feature("tblp1").set("legend", true);
    model.result("pg2").feature("tblp1").set("legendmethod", "manual");
    model.result("pg2").feature("tblp1").setIndex("legends", "\u6750\u6599\u5b9e\u4f8b 1", 0);
    model.result("pg2").feature("tblp1").setIndex("legends", "\u6750\u6599\u5b9e\u4f8b 2", 1);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u4f4d\u79fb vs. FOS");
    model.result("pg3").set("titletype", "none");
    model.result("pg3").set("legendpos", "upperleft");
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "\u5b89\u5168\u7cfb\u6570 (1)");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").set("data", "dset3");
    model.result("pg3").feature("glob1").setIndex("looplevelinput", "manual", 2);
    model.result("pg3").feature("glob1").setIndex("looplevel", new int[]{1}, 2);
    model.result("pg3").feature("glob1").setIndex("expr", "maxop1(solid.disp)", 0);
    model.result("pg3").feature("glob1").setIndex("descr", "\u4f4d\u79fb", 0);
    model.result("pg3").feature("glob1").set("legendmethod", "evaluated");
    model.result("pg3").feature("glob1").set("legendpattern", "\\alpha = eval(alpha,deg)<sup>\\circ</sub>");
    model.result("pg3").feature().duplicate("glob2", "glob1");
    model.result("pg3").run();
    model.result("pg3").feature("glob2").setIndex("looplevel", new int[]{2}, 2);
    model.result("pg3").feature("glob2").set("linestyle", "dashed");
    model.result("pg3").feature("glob2").set("linecolor", "cyclereset");
    model.result("pg3").feature("glob2").set("legend", false);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").setIndex("looplevel", 8, 0);
    model.result("pg4").setIndex("looplevel", 4, 1);
    model.result("pg4").setIndex("looplevel", 2, 2);
    model.result("pg4").label("\u7b49\u6548\u5851\u6027\u5e94\u53d8 (solid)");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"solid.epeGp"});
    model.result("pg4").feature("surf1").set("inheritplot", "none");
    model.result("pg4").feature("surf1").set("resolution", "normal");
    model.result("pg4").feature("surf1").set("colortabletype", "discrete");
    model.result("pg4").feature("surf1").set("bandcount", 11);
    model.result("pg4").feature("surf1").set("colortable", "AuroraAustralisDark");
    model.result("pg4").feature("surf1").set("descractive", true);
    model.result("pg4").feature("surf1").set("descr", "\u7b49\u6548\u5851\u6027\u5e94\u53d8");
    model.result("pg4").label("\u7b49\u6548\u5851\u6027\u5e94\u53d8 (solid)");
    model.result("pg4").run();
    model.result("pg4").label("\u7b49\u6548\u5851\u6027\u5e94\u53d8\uff08\u5b9e\u4f8b 1\uff09");
    model.result("pg4").set("data", "none");
    model.result("pg4").set("titletype", "custom");
    model.result("pg4").set("solutionintitle", false);
    model.result("pg4").set("edges", false);
    model.result("pg4").set("plotarrayenable", true);
    model.result("pg4").set("arrayshape", "square");
    model.result("pg4").feature("surf1").set("arraydim", "2");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("data", "dset3");
    model.result("pg4").feature("surf1").set("looplevel", new int[]{8, 1, 1});
    model.result("pg4").feature().duplicate("surf2", "surf1");
    model.result("pg4").feature("surf2").set("arraydim", "2");
    model.result("pg4").run();
    model.result("pg4").feature("surf2").set("looplevel", new int[]{8, 2, 1});
    model.result("pg4").feature("surf2").set("titletype", "none");
    model.result("pg4").feature("surf2").set("inheritplot", "surf1");
    model.result("pg4").feature().duplicate("surf3", "surf2");
    model.result("pg4").feature("surf3").set("arraydim", "2");
    model.result("pg4").run();
    model.result("pg4").feature("surf3").set("looplevel", new int[]{8, 3, 1});
    model.result("pg4").feature().duplicate("surf4", "surf3");
    model.result("pg4").feature("surf4").set("arraydim", "2");
    model.result("pg4").run();
    model.result("pg4").feature("surf4").set("looplevel", new int[]{8, 4, 1});
    model.result("pg4").run();
    model.result("pg4").create("tlan1", "TableAnnotation");
    model.result("pg4").feature("tlan1").set("arraydim", "2");
    model.result("pg4").feature("tlan1").set("source", "localtable");
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 25, 0, 0);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 0, 0, 1);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", "\\[\\alpha \\;=\\;15^\\circ\\]", 0, 2);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 125, 1, 0);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 0, 1, 1);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", "\\[\\alpha \\;=\\;25^\\circ\\]", 1, 2);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 25, 2, 0);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 55, 2, 1);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", "\\[\\alpha \\;=\\;35^\\circ\\]", 2, 2);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 125, 3, 0);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 55, 3, 1);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", "\\[\\alpha \\;=\\;45^\\circ\\]", 3, 2);
    model.result("pg4").feature("tlan1").set("latexmarkup", true);
    model.result("pg4").feature("tlan1").set("showpoint", false);
    model.result("pg4").feature("surf1").set("arraydim", "2");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("looplevel", new int[]{22, 1, 1});
    model.result("pg4").feature("surf1").set("rangecoloractive", true);
    model.result("pg4").feature("surf1").set("rangecolormax", 0.25);
    model.result("pg4").feature("surf1").set("bandcount", 5);
    model.result("pg4").feature("surf2").set("arraydim", "2");
    model.result("pg4").run();
    model.result("pg4").feature("surf2").set("looplevel", new int[]{13, 2, 1});
    model.result("pg4").feature("surf3").set("arraydim", "2");
    model.result("pg4").run();
    model.result("pg4").feature("surf3").set("looplevel", new int[]{10, 3, 1});
    model.result("pg4").feature("surf4").set("arraydim", "2");
    model.result("pg4").run();
    model.result("pg4").feature("surf4").set("looplevel", new int[]{9, 4, 1});
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u7b49\u6548\u5851\u6027\u5e94\u53d8\uff08\u5b9e\u4f8b 2\uff09");
    model.result("pg5").feature("surf1").set("arraydim", "2");
    model.result("pg5").run();
    model.result("pg5").feature("surf1").set("looplevel", new int[]{20, 1, 2});
    model.result("pg5").feature("surf2").set("arraydim", "2");
    model.result("pg5").run();
    model.result("pg5").feature("surf2").set("looplevel", new int[]{13, 2, 2});
    model.result("pg5").feature("surf3").set("arraydim", "2");
    model.result("pg5").run();
    model.result("pg5").feature("surf3").set("looplevel", new int[]{9, 3, 2});
    model.result("pg5").feature("surf4").set("arraydim", "2");
    model.result("pg5").run();
    model.result("pg5").feature("surf4").set("looplevel", new int[]{8, 4, 2});
    model.result("pg5").run();
    model.result().dataset().create("extr1", "Extrude2D");
    model.result().dataset("extr1").set("data", "dset3");
    model.result().dataset("extr1").set("zmax", "L1+L2");
    model.result().dataset("extr1").set("planemap", "xz");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").run();
    model.result("pg6").label("\u4f4d\u79fb");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").create("def1", "Deform");
    model.result("pg6").run();
    model.result("pg6").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg6").feature("surf1").feature("def1").set("scale", 1);
    model.result("pg6").run();

    model.title("\u57fa\u4e8e\u5f3a\u5ea6\u6298\u51cf\u6cd5\u7684\u8fb9\u5761\u7a33\u5b9a\u6027\u5206\u6790");

    model
         .description("\u5f3a\u5ea6\u6298\u51cf\u6cd5\u662f\u4e00\u79cd\u5728\u5ca9\u571f\u5de5\u7a0b\u95ee\u9898\uff08\u5c24\u5176\u662f\u8fb9\u5761\u7a33\u5b9a\u6027\uff09\u4e2d\u786e\u5b9a\u5b89\u5168\u7cfb\u6570 (FOS) \u7684\u5de5\u5177\u3002\u4e3a\u4e86\u786e\u5b9a\u8fb9\u5761\u7684\u5b89\u5168\u7cfb\u6570\uff0c\u6211\u4eec\u9010\u6e10\u964d\u4f4e\u83ab\u5c14-\u5e93\u4ed1\u6a21\u578b\u7684\u5f3a\u5ea6\u5c5e\u6027\uff0c\u76f4\u5230\u53d1\u751f\u7834\u574f\u3002\u672c\u4f8b\u5bf9\u5173\u8054\u548c\u975e\u5173\u8054\u5851\u6027\u6d41\u52a8\u8fdb\u884c\u4e86\u6bd4\u8f83\u3002");

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

    model.label("strength_reduction_method.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
