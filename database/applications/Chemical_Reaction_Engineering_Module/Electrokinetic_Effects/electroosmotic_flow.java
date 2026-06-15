/*
 * electroosmotic_flow.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:17 by COMSOL 6.3.0.290. */
public class electroosmotic_flow {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Electrokinetic_Effects");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ec", "ConductiveMedia", "geom1");
    model.component("comp1").physics().create("g", "GeneralFormPDE", "geom1");
    model.component("comp1").physics("g").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics("g").feature("gfeq1").set("Ga", new String[][]{{"-ux", "-uy"}});
    model.component("comp1").physics().create("tds", "DilutedSpecies", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/ec", true);
    model.study("std1").feature("stat").setSolveFor("/physics/g", true);
    model.study("std1").feature("stat").setSolveFor("/physics/tds", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("T", "298[K]", "\u6e29\u5ea6");
    model.param().set("eps_p", "0.6", "\u5b54\u9699\u7387");
    model.param().set("a", "10[um]", "\u5e73\u5747\u5b54\u9699\u534a\u5f84");
    model.param().set("kappa0", "3.5e-4[S/m]", "\u7535\u89e3\u8d28\u7535\u5bfc\u7387");
    model.param().set("V_anode", "50[V]", "\u9633\u6781\u7535\u4f4d");
    model.param().set("eps_w", "80.2*epsilon0_const", "\u4ecb\u7535\u5e38\u6570");
    model.param().set("eta", "1e-3[Pa*s]", "\u52a8\u529b\u9ecf\u5ea6");
    model.param().set("zeta", "-0.1[V]", "Zeta \u7535\u4f4d");
    model.param().set("p1", "0.01*1.013e5[Pa]", "\u5165\u53e3\u538b\u529b");
    model.param()
         .set("k_p", "eps_p*a^2/(8*eta)", "\u524d\u56e0\u5b50\uff0c\u6d41\u52a8-\u901f\u5ea6\u538b\u529b\u9879");
    model.param()
         .set("k_V", "eps_p*eps_w*zeta/eta", "\u524d\u56e0\u5b50\uff0c\u6d41\u52a8-\u901f\u5ea6\u7535\u6e17\u9879");
    model.param().set("D", "1e-9[m^2/s]", "\u793a\u8e2a\u5242\u6269\u6563\u7cfb\u6570");
    model.param().set("zn", "1", "\u793a\u8e2a\u79bb\u5b50\u7535\u8377\u6570");
    model.param().set("ctop", "1[mmol/m^3]", "\u793a\u8e2a\u5242\u6700\u5927\u521d\u59cb\u6d53\u5ea6");
    model.param()
         .set("p_w", "0.2[mm]", "\u793a\u8e2a\u5242\u6d53\u5ea6\u5206\u5e03\u66f2\u7ebf\u5cf0\u503c\u5bbd\u5ea6");
    model.param()
         .set("x_m", "3[mm]", "\u51e0\u4f55\u5185\u7684\u793a\u8e2a\u5242\u6d53\u5ea6\u5206\u5e03\u66f2\u7ebf\u5cf0\u503c\u4f4d\u7f6e");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("u_p", "-k_p*px", "\u901f\u5ea6\u538b\u529b\u9879\uff0cx \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("v_p", "-k_p*py", "\u901f\u5ea6\u538b\u529b\u9879\uff0cy \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("U_p", "sqrt(u_p^2+v_p^2)", "\u901f\u5ea6\u538b\u529b\u9879\uff0c\u5927\u5c0f");
    model.component("comp1").variable("var1")
         .set("u_eo", "k_V*Vx", "\u901f\u5ea6\u7535\u6e17\u9879\uff0cx \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("v_eo", "k_V*Vy", "\u901f\u5ea6\u7535\u6e17\u9879\uff0cy \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("U_eo", "sqrt(u_eo^2+v_eo^2)", "\u6d41\u52a8-\u901f\u5ea6\u7535\u6e17\u9879\uff0c\u5927\u5c0f");
    model.component("comp1").variable("var1").set("u_flow", "u_p+u_eo", "\u901f\u5ea6\uff0cx \u5206\u91cf");
    model.component("comp1").variable("var1").set("v_flow", "v_p+v_eo", "\u901f\u5ea6\uff0cy \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("U_flow", "sqrt(u_flow^2+v_flow^2)", "\u901f\u5ea6\uff0c\u5927\u5c0f");
    model.component("comp1").variable("var1")
         .set("c_init", "ctop*exp(-0.5*((x-x_m)/p_w)^2)", "\u521d\u59cb\u6d53\u5ea6\u5206\u5e03");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{8, 3});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new int[]{-4, 0});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new double[]{0.2, 1.5});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new int[]{-2, 0});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", 0.1);
    model.component("comp1").geom("geom1").feature("c1").set("pos", new double[]{-1.9, 1.5});
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("c1", "r2");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("copy1").selection("input").set("uni1");
    model.component("comp1").geom("geom1").feature("copy1").set("displx", 3.8);
    model.component("comp1").geom("geom1").run("copy1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("copy1", "uni1");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u9634\u6781");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("dif1", 4, 5, 11, 12);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("\u9633\u6781");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("dif1", 7, 8, 13, 14);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").label("\u5165\u53e3");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("dif1", 1);
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("sel4", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel4").label("\u51fa\u53e3");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").set("dif1", 10);
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("ec").feature("cucn1").set("sigma_mat", "userdef");
    model.component("comp1").physics("ec").feature("cucn1")
         .set("sigma", new String[]{"kappa0", "0", "0", "0", "kappa0", "0", "0", "0", "kappa0"});
    model.component("comp1").physics("ec").feature("cucn1").set("epsilonr_mat", "userdef");
    model.component("comp1").physics("ec").create("pot1", "ElectricPotential", 1);
    model.component("comp1").physics("ec").feature("pot1").selection().named("geom1_sel1");
    model.component("comp1").physics("ec").create("pot2", "ElectricPotential", 1);
    model.component("comp1").physics("ec").feature("pot2").selection().named("geom1_sel2");
    model.component("comp1").physics("ec").feature("pot2").set("V0", "V_anode");
    model.component("comp1").physics("g").label("\u7535\u6e17\u538b\u529b");
    model.component("comp1").physics("g").prop("Units").set("DependentVariableQuantity", "pressure");
    model.component("comp1").physics("g").prop("Units").setIndex("CustomSourceTermUnit", "1/s", 0, 0);
    model.component("comp1").physics("g").field("dimensionless").field("p");
    model.component("comp1").physics("g").field("dimensionless").component(1, "p");
    model.component("comp1").physics("g").feature("gfeq1").setIndex("Ga", new String[]{"u_flow", "-uy"}, 0);
    model.component("comp1").physics("g").feature("gfeq1").setIndex("Ga", new String[]{"u_flow", "v_flow"}, 0);
    model.component("comp1").physics("g").feature("gfeq1").setIndex("f", 0, 0);
    model.component("comp1").physics("g").feature("gfeq1").setIndex("da", 0, 0);
    model.component("comp1").physics("g").create("dir1", "DirichletBoundary", 1);
    model.component("comp1").physics("g").feature("dir1").label("\u5165\u53e3 - p=0");
    model.component("comp1").physics("g").feature("dir1").selection().named("geom1_sel3");
    model.component("comp1").physics("g").create("dir2", "DirichletBoundary", 1);
    model.component("comp1").physics("g").feature("dir2").label("\u51fa\u53e3 - p=p1");
    model.component("comp1").physics("g").feature("dir2").selection().named("geom1_sel4");
    model.component("comp1").physics("g").feature("dir2").setIndex("r", "p1", 0);
    model.component("comp1").physics("tds").prop("TransportMechanism").set("Migration", true);
    model.component("comp1").physics("tds").feature("sp1").setIndex("z", "zn", 0);
    model.component("comp1").physics("tds").feature("cdm1").set("minput_temperature_src", "userdef");
    model.component("comp1").physics("tds").feature("cdm1").set("minput_temperature", "T");
    model.component("comp1").physics("tds").feature("cdm1")
         .set("D_c", new String[]{"D", "0", "0", "0", "D", "0", "0", "0", "D"});
    model.component("comp1").physics("tds").feature("cdm1").set("u", new String[]{"u_flow", "v_flow", "0"});
    model.component("comp1").physics("tds").feature("cdm1").set("V_src", "root.comp1.V");
    model.component("comp1").physics("tds").feature("init1").setIndex("initc", "c_init", 0);
    model.component("comp1").physics("tds").create("fl1", "FluxBoundary", 1);
    model.component("comp1").physics("tds").feature("fl1").selection().set(1, 10);
    model.component("comp1").physics("tds").feature("fl1").setIndex("species", true, 0);
    model.component("comp1").physics("tds").feature("fl1").setIndex("J0", "-tds.nmflux_c", 0);

    model.component("comp1").mesh("mesh1").autoMeshSize(1);

    model.study("std1").feature("stat").setSolveFor("/physics/tds", false);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("tlist", "range(0,0.1,2)");
    model.study("std1").feature("time").set("usertol", true);
    model.study("std1").feature("time").set("rtol", 0.005);
    model.study("std1").feature("time").setSolveFor("/physics/ec", false);
    model.study("std1").feature("time").setSolveFor("/physics/g", false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u52bf (ec)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").feature("surf1").set("colortable", "Dipole");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature().create("str1", "Streamline");
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("solutionparams", "parent");
    model.result("pg1").feature("str1").set("expr", new String[]{"ec.Ex", "ec.Ey"});
    model.result("pg1").feature("str1").set("titletype", "none");
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("udist", 0.02);
    model.result("pg1").feature("str1").set("maxlen", 0.4);
    model.result("pg1").feature("str1").set("maxsteps", 5000);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("inheritcolor", false);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("data", "parent");
    model.result("pg1").feature("str1").selection().geom("geom1", 1);
    model.result("pg1").feature("str1").selection().set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
    model.result("pg1").feature("str1").set("inheritplot", "surf1");
    model.result("pg1").feature("str1").feature().create("col1", "Color");
    model.result("pg1").feature("str1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg1").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg1").feature("str1").feature().create("filt1", "Filter");
    model.result("pg1").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u7535\u573a (ec)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("solutionparams", "parent");
    model.result("pg2").feature("surf1").set("expr", "ec.normE");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature().create("str1", "Streamline");
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("solutionparams", "parent");
    model.result("pg2").feature("str1").set("expr", new String[]{"ec.Ex", "ec.Ey"});
    model.result("pg2").feature("str1").set("titletype", "none");
    model.result("pg2").feature("str1").set("posmethod", "uniform");
    model.result("pg2").feature("str1").set("udist", 0.02);
    model.result("pg2").feature("str1").set("maxlen", 0.4);
    model.result("pg2").feature("str1").set("maxsteps", 5000);
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("inheritcolor", false);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("data", "parent");
    model.result("pg2").feature("str1").selection().geom("geom1", 1);
    model.result("pg2").feature("str1").selection().set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
    model.result("pg2").feature("str1").set("inheritplot", "surf1");
    model.result("pg2").feature("str1").feature().create("col1", "Color");
    model.result("pg2").feature("str1").feature("col1").set("expr", "ec.normE");
    model.result("pg2").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg2").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg2").feature("str1").feature().create("filt1", "Filter");
    model.result("pg2").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 21, 0);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").label("\u7535\u6e17\u538b\u529b");
    model.result("pg3").feature("surf1").set("expr", "p");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 21, 0);
    model.result("pg4").label("\u6d53\u5ea6 (tds)");
    model.result("pg4").set("titletype", "custom");
    model.result("pg4").set("prefixintitle", "");
    model.result("pg4").set("expressionintitle", false);
    model.result("pg4").set("typeintitle", false);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"c"});
    model.result("pg4").feature("surf1").set("colortable", "Prism");
    model.result("pg4").set("typeintitle", true);
    model.result("pg4").create("arws1", "ArrowSurface");
    model.result("pg4").feature("arws1").set("expr", new String[]{"tds.tflux_cx", "tds.tflux_cy"});
    model.result("pg4").feature("arws1").set("xnumber", 10);
    model.result("pg4").feature("arws1").set("ynumber", 10);
    model.result("pg4").feature("arws1").set("color", "black");
    model.result("pg4").feature("arws1").create("sel1", "Selection");
    model.result("pg4").feature("arws1").feature("sel1").selection().set(1);
    model.result("pg1").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("arws1").active(false);
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("data", "dset1");
    model.result("pg4").feature("surf1").setIndex("looplevel", 1, 0);
    model.result("pg4").feature("surf1").set("colortable", "TrafficLight");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").setIndex("looplevel", 7, 0);
    model.result("pg4").run();
    model.result("pg4").feature("surf1").setIndex("looplevel", 13, 0);
    model.result("pg4").run();
    model.result("pg4").feature("surf1").setIndex("looplevel", 19, 0);
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").run();
    model.result("pg5").label("\u901f\u5ea6\u7535\u6e17\u9879");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg5").set("titletype", "none");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").label("\u901f\u5ea6\u5927\u5c0f");
    model.result("pg5").feature("surf1").set("expr", "U_eo");
    model.result("pg5").feature("surf1")
         .set("descr", "\u6d41\u52a8-\u901f\u5ea6\u7535\u6e17\u9879\uff0c\u5927\u5c0f");
    model.result("pg5").feature("surf1").set("unit", "mm/s");
    model.result("pg5").run();
    model.result("pg5").create("arws1", "ArrowSurface");
    model.result("pg5").feature("arws1").label("\u901f\u5ea6\u573a");
    model.result("pg5").feature("arws1").set("expr", new String[]{"u_eo", "v_eo"});
    model.result("pg5").feature("arws1").set("descractive", true);
    model.result("pg5").feature("arws1").set("descr", "\u901f\u5ea6\u7535\u6e17\u9879");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").run();
    model.result("pg6").label("\u901f\u5ea6\u538b\u529b\u9879");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").set("showlegendsunit", true);
    model.result("pg6").set("titletype", "none");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").label("\u901f\u5ea6\u5927\u5c0f");
    model.result("pg6").feature("surf1").set("expr", "U_p");
    model.result("pg6").feature("surf1").set("descr", "\u901f\u5ea6\u538b\u529b\u9879\uff0c\u5927\u5c0f");
    model.result("pg6").feature("surf1").set("unit", "mm/s");
    model.result("pg6").run();
    model.result("pg6").create("arws1", "ArrowSurface");
    model.result("pg6").feature("arws1").label("\u901f\u5ea6\u573a");
    model.result("pg6").feature("arws1").set("expr", new String[]{"u_p", "v_p"});
    model.result("pg6").feature("arws1").set("descractive", true);
    model.result("pg6").feature("arws1").set("descr", "\u901f\u5ea6\u538b\u529b\u9879");
    model.result("pg6").run();
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").setIndex("genpoints", -4, 0, 0);
    model.result().dataset("cln1").setIndex("genpoints", 2.5, 0, 1);
    model.result().dataset("cln1").setIndex("genpoints", 4, 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", 2.5, 1, 1);
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u6cbf x \u8f74 y=2.5 mm \u76f4\u7ebf\u7684\u6d53\u5ea6");
    model.result("pg7").set("data", "cln1");
    model.result("pg7").setIndex("looplevelinput", "manual", 0);
    model.result("pg7").setIndex("looplevel", new int[]{1, 6, 11, 16, 21}, 0);
    model.result("pg7").set("legendlayout", "outside");
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg7").feature("lngr1").set("linewidth", "preference");
    model.result("pg7").feature("lngr1").set("expr", "c");
    model.result("pg7").feature("lngr1").set("descr", "\u6469\u5c14\u6d53\u5ea6\uff0cc");
    model.result("pg7").feature("lngr1").set("xdataexpr", "x");
    model.result("pg7").feature("lngr1").set("xdatadescr", "x \u5750\u6807");
    model.result("pg7").feature("lngr1").set("legend", true);
    model.result("pg7").run();
    model.result("pg3").run();
    model.result("pg3").label("\u538b\u529b");

    model.title("\u591a\u5b54\u4ecb\u8d28\u4e2d\u7684\u7535\u6e17\u6d41");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u591a\u5b54\u4ecb\u8d28\u4e2d\u7684\u7535\u6e17\u6d41\u3002\u8be5\u7cfb\u7edf\u7531\u70e7\u7ed3\u7684\u591a\u5b54\u6750\u6599\u533a\u548c\u751f\u6210\u7535\u573a\u7684\u4e24\u4e2a\u7535\u6781\u7ec4\u6210\u3002\u5355\u5143\u5305\u542b\u538b\u529b\u6d41\u548c\u7535\u6e17\u9a71\u52a8\u7684\u6d41\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("electroosmotic_flow.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
