/*
 * squeeze_film_porous.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:32 by COMSOL 6.3.0.290. */
public class squeeze_film_porous {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\CFD_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("tff", "ThinFilmFlowShell", "geom1");
    model.component("comp1").physics().create("dl", "PorousMediaFlowDarcy", "geom1");

    model.component("comp1").multiphysics().create("tfpf1", "ThinFilmAndPorousMediaFlow", 2);
    model.component("comp1").multiphysics("tfpf1").set("ThinFilmFluid_physics", "tff");
    model.component("comp1").multiphysics("tfpf1").set("PorousMedia_physics", "dl");
    model.component("comp1").multiphysics("tfpf1").selection().all();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/tff", true);
    model.study("std1").feature("stat").setSolveFor("/physics/dl", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/tfpf1", true);

    model.baseSystem("none");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("k", "1", "\u677f\u7684\u957f\u5bbd\u6bd4");
    model.param().set("a", "1", "\u677f\u957f");
    model.param().set("b", "a/k", "\u677f\u5bbd");
    model.param().set("Hbar", "0.02", "\u591a\u5b54\u57df\u539a\u5ea6\u4e0e\u677f\u957f\u4e4b\u6bd4");
    model.param().set("H", "Hbar*a", "\u591a\u5b54\u57df\u539a\u5ea6");
    model.param().set("rho", "1", "\u6d41\u4f53\u5bc6\u5ea6");
    model.param().set("eta", "1", "\u6d41\u4f53\u9ecf\u5ea6");
    model.param().set("psi", "0.1", "\u6e17\u900f\u7387\u53c2\u6570");
    model.param().set("phi", "psi*ht^3/H", "\u591a\u5b54\u57df\u7684\u6e17\u900f\u7387");
    model.param().set("sqvel", "1", "\u6324\u538b\u901f\u5ea6");
    model.param().set("ht", "0.001", "\u6d41\u4f53\u8584\u819c\u7684\u9ad8\u5ea6");

    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").set("funcname", "a_m");
    model.component("comp1").func("an1").set("expr", "m*pi/a");
    model.component("comp1").func("an1").set("args", "m, a");
    model.component("comp1").func("an1").setIndex("plotargs", 29, 0, 2);
    model.component("comp1").func().create("an2", "Analytic");
    model.component("comp1").func("an2").set("funcname", "b_n");
    model.component("comp1").func("an2").set("expr", "n*pi/b");
    model.component("comp1").func("an2").set("args", "n, b");
    model.component("comp1").func("an2").setIndex("plotargs", 29, 0, 2);
    model.component("comp1").func().create("an3", "Analytic");
    model.component("comp1").func("an3").set("funcname", "gamma_mn");
    model.component("comp1").func("an3").set("expr", "pi*sqrt(m^2+(k*n)^2)/a");
    model.component("comp1").func("an3").set("args", "m, n, k, a");
    model.component("comp1").func("an3").setIndex("plotargs", 29, 0, 2);
    model.component("comp1").func("an3").setIndex("plotargs", 29, 1, 2);
    model.component("comp1").func("an3").setIndex("plotargs", 8, 2, 2);
    model.component("comp1").func().create("an4", "Analytic");
    model.component("comp1").func("an4").set("funcname", "G_mn");
    model.component("comp1").func("an4").set("expr", "12*(exp(2*gamma_mn*H)-1)/(H*(exp(2*gamma_mn*H)+1)/a)");
    model.component("comp1").func("an4").set("args", "gamma_mn, H, a");
    model.component("comp1").func("an4").setIndex("plotargs", "1e6", 0, 2);
    model.component("comp1").func().create("an5", "Analytic");
    model.component("comp1").func("an5").set("funcname", "p_summand");
    model.component("comp1").func("an5")
         .set("expr", "(sin(a_m((2*m-1),a)*x)*sin(b_n((2*n-1),b)*y))/((2*m-1)*(2*n-1)*sqrt((2*m-1)^2+(k*(2*n-1))^2)*(pi*sqrt((2*m-1)^2+(k*(2*n-1))^2)+(psi*G_mn(gamma_mn((2*m-1),(2*n-1),k,a),H,a))))");
    model.component("comp1").func("an5").set("args", "m, n, x, y, a, b, psi, k, H");
    model.component("comp1").func("an5").setIndex("plotargs", 29, 0, 2);
    model.component("comp1").func("an5").setIndex("plotargs", 29, 1, 2);
    model.component("comp1").func("an5").setIndex("plotargs", "a", 2, 2);
    model.component("comp1").func().create("an6", "Analytic");
    model.component("comp1").func("an6").set("funcname", "W_summand_psi");
    model.component("comp1").func("an6")
         .set("expr", "1/(((2*m-1)*(2*n-1))^2*sqrt((2*m-1)^2+(k*(2*n-1))^2)*((pi*sqrt((2*m-1)^2+(k*(2*n-1))^2))+(psi*G_mn(gamma_mn((2*m-1),(2*n-1),k,a),H,a))))");
    model.component("comp1").func("an6").set("args", "m, n, a, psi, k, H");
    model.component("comp1").func("an6").setIndex("plotargs", 29, 0, 2);
    model.component("comp1").func("an6").setIndex("plotargs", 29, 1, 2);
    model.component("comp1").func().create("an7", "Analytic");
    model.component("comp1").func("an7").set("funcname", "W_summand_phi");
    model.component("comp1").func("an7")
         .set("expr", "1/(((2*m-1)*(2*n-1))^2*sqrt((2*m-1)^2+(k*(2*n-1))^2)*((pi*sqrt((2*m-1)^2+(k*(2*n-1))^2))+(phi*H/ht^3*G_mn(gamma_mn((2*m-1),(2*n-1),k,a),H,a))))");
    model.component("comp1").func("an7").set("args", "m, n, a, phi, k, H");
    model.component("comp1").func("an7").setIndex("plotargs", 29, 0, 2);
    model.component("comp1").func("an7").setIndex("plotargs", 29, 1, 2);

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"a/2", "b/2", "H"});
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u8584\u819c\u8fb9\u754c");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(3);
    model.component("comp1").selection("sel1").set("color", "1");
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u5bf9\u79f0\u8fb9\u754c");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(5, 6);
    model.component("comp1").selection("sel2").set("color", "4");

    model.component("comp1").view("view1").set("showgrid", false);
    model.component("comp1").view("view1").set("transparency", false);

    model.component("comp1").physics("tff").selection().named("sel1");
    model.component("comp1").physics("tff").prop("ReferencePressure").set("pref", 0);
    model.component("comp1").physics("tff").feature("ffp1").set("minput_temperature_src", "fromCommonDef");
    model.component("comp1").physics("tff").feature("ffp1").set("WallNormal", "InverseOrientation");
    model.component("comp1").physics("tff").feature("ffp1").set("hw1", "ht");
    model.component("comp1").physics("tff").feature("ffp1").set("TangentialWallVelocity", "userdef");
    model.component("comp1").physics("tff").feature("ffp1").set("vw", new String[]{"0", "0", "sqvel"});
    model.component("comp1").physics("tff").feature("ffp1").set("mure_mat", "userdef");
    model.component("comp1").physics("tff").feature("ffp1").set("mure", "eta");
    model.component("comp1").physics("tff").feature("ffp1").set("rho_mat", "userdef");
    model.component("comp1").physics("tff").feature("ffp1").set("rho", "rho");
    model.component("comp1").physics("tff").create("sym1", "SymmetryFluid", 1);
    model.component("comp1").physics("tff").feature("sym1").selection().set(7, 10);
    model.component("comp1").physics("dl").prop("PhysicalModelProperty").set("pref", 0);
    model.component("comp1").physics("dl").feature("porous1").feature("fluid1").set("rho_mat", "userdef");
    model.component("comp1").physics("dl").feature("porous1").feature("fluid1").set("rho", "rho");
    model.component("comp1").physics("dl").feature("porous1").feature("fluid1").set("mu_mat", "userdef");
    model.component("comp1").physics("dl").feature("porous1").feature("fluid1").set("mu", "eta");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1").set("epsilon_mat", "userdef");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1").set("kappa_mat", "userdef");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1")
         .set("kappa", new String[]{"phi", "0", "0", "0", "phi", "0", "0", "0", "phi"});
    model.component("comp1").physics("dl").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("dl").feature("sym1").selection().named("sel2");
    model.component("comp1").physics("dl").create("pr1", "Pressure", 2);
    model.component("comp1").physics("dl").feature("pr1").selection().set(1, 2);

    model.component("comp1").mesh("mesh1").autoMeshSize(1);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().named("sel1");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 4);
    model.component("comp1").mesh("mesh1").feature("ftet1").active(false);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "k", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "k", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param")
         .setIndex("plistarr", "8*1^range(1,5) 4*1^range(1,5) 2*1^range(1,5) 1^range(1,5) 1^range(1,15)", 0);
    model.study("std1").feature("param").setIndex("pname", "a", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "", 1);
    model.study("std1").feature("param").setIndex("pname", "a", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "", 1);
    model.study("std1").feature("param").setIndex("pname", "psi", 1);
    model.study("std1").feature("param")
         .setIndex("plistarr", "10^{range(0,-1,-4)} 10^{range(0,-1,-4)} 10^{range(0,-1,-4)} 10^{range(0,-1,-4)} range(0.1,0.1,0.5) range(0.01,0.01,0.05) range(0.001,0.001,0.005)", 1);
    model.study("std1").feature("param").setIndex("pname", "a", 2);
    model.study("std1").feature("param").setIndex("plistarr", "", 2);
    model.study("std1").feature("param").setIndex("punit", "", 2);
    model.study("std1").feature("param").setIndex("pname", "a", 2);
    model.study("std1").feature("param").setIndex("plistarr", "", 2);
    model.study("std1").feature("param").setIndex("punit", "", 2);
    model.study("std1").feature("param").setIndex("pname", "Hbar", 2);
    model.study("std1").feature("param")
         .setIndex("plistarr", "0.02*1^range(1,20) range(0.02,0.02,0.1) range(0.02,0.02,0.1) range(0.02,0.02,0.1)", 2);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().setOnlyPlotWhenRequested(true);
    model.result().dataset().create("mir1", "Mirror3D");
    model.result().dataset("mir1").set("data", "dset2");
    model.result().dataset("mir1").set("quickplane", "xz");
    model.result().dataset("mir1").set("quicky", "b/2");
    model.result().dataset().create("mir2", "Mirror3D");
    model.result().dataset("mir2").set("quickx", "a/2");
    model.result().dataset("mir2").set("data", "mir1");
    model.result().dataset().create("cln1", "CutLine3D");
    model.result().dataset("cln1").set("data", "mir2");
    model.result().dataset("cln1").setIndex("genpoints", "b/2", 0, 1);
    model.result().dataset("cln1").setIndex("genpoints", "a", 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", "b/2", 1, 1);
    model.result().dataset().create("grid1", "Grid1D");
    model.result().dataset("grid1").set("source", "function");
    model.result().dataset("grid1").set("function", "all");
    model.result().dataset("grid1").set("par1", "psi");
    model.result().dataset("grid1").set("parmin1", "1e-4");
    model.result().dataset().create("grid2", "Grid1D");
    model.result().dataset("grid2").set("source", "function");
    model.result().dataset("grid2").set("function", "all");
    model.result().dataset("grid2").set("par1", "k");
    model.result().dataset("grid2").set("parmin1", 1);
    model.result().dataset("grid2").set("parmax1", 8);
    model.result().dataset().create("grid3", "Grid1D");
    model.result().dataset("grid3").set("source", "function");
    model.result().dataset("grid3").set("function", "all");
    model.result().dataset("grid3").set("par1", "H");
    model.result().dataset("grid3").set("parmin1", 0.02);
    model.result().dataset("grid3").set("parmax1", 0.1);
    model.result().numerical().create("int1", "IntSurface");
    model.result().numerical("int1").set("intvolume", true);
    model.result().numerical("int1").set("data", "mir2");
    model.result().numerical("int1").setIndex("looplevelinput", "manualindices", 0);
    model.result().numerical("int1").setIndex("looplevelindices", "range(1,1,5)", 0);
    model.result().numerical("int1").selection().named("sel1");
    model.result().numerical("int1").setIndex("expr", "p*ht^3/(eta*a^3*b*sqvel)", 0);
    model.result().numerical().duplicate("int2", "int1");
    model.result().numerical("int2").setIndex("looplevelindices", "range(5,1,10)", 0);
    model.result().numerical().duplicate("int3", "int2");
    model.result().numerical("int3").setIndex("looplevelindices", "range(10,1,15)", 0);
    model.result().numerical().duplicate("int4", "int3");
    model.result().numerical("int4").setIndex("looplevelindices", "range(15,1,20)", 0);
    model.result().numerical().duplicate("int5", "int4");
    model.result().numerical("int5").setIndex("looplevelindices", "range(1,5,20)", 0);
    model.result().numerical().duplicate("int6", "int5");
    model.result().numerical("int6").setIndex("looplevelindices", "range(2,5,20)", 0);
    model.result().numerical().duplicate("int7", "int6");
    model.result().numerical("int7").setIndex("looplevelindices", "range(3,5,20)", 0);
    model.result().numerical().duplicate("int8", "int7");
    model.result().numerical("int8").setIndex("looplevelindices", "range(4,5,20)", 0);
    model.result().numerical().duplicate("int9", "int8");
    model.result().numerical("int9").setIndex("looplevelindices", "range(21,1,25)", 0);
    model.result().numerical().duplicate("int10", "int9");
    model.result().numerical("int10").setIndex("looplevelindices", "range(26,1,30)", 0);
    model.result().numerical().duplicate("int11", "int10");
    model.result().numerical("int11").setIndex("looplevelindices", "range(31,1,35)", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u8868\u9762\u79ef\u5206 1");
    model.result().numerical("int1").set("table", "tbl1");
    model.result().numerical("int1").setResult();
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u8868\u9762\u79ef\u5206 2");
    model.result().numerical("int2").set("table", "tbl2");
    model.result().numerical("int2").setResult();
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").comments("\u8868\u9762\u79ef\u5206 3");
    model.result().numerical("int3").set("table", "tbl3");
    model.result().numerical("int3").setResult();
    model.result().table().create("tbl4", "Table");
    model.result().table("tbl4").comments("\u8868\u9762\u79ef\u5206 4");
    model.result().numerical("int4").set("table", "tbl4");
    model.result().numerical("int4").setResult();
    model.result().table().create("tbl5", "Table");
    model.result().table("tbl5").comments("\u8868\u9762\u79ef\u5206 5");
    model.result().numerical("int5").set("table", "tbl5");
    model.result().numerical("int5").setResult();
    model.result().table().create("tbl6", "Table");
    model.result().table("tbl6").comments("\u8868\u9762\u79ef\u5206 6");
    model.result().numerical("int6").set("table", "tbl6");
    model.result().numerical("int6").setResult();
    model.result().table().create("tbl7", "Table");
    model.result().table("tbl7").comments("\u8868\u9762\u79ef\u5206 7");
    model.result().numerical("int7").set("table", "tbl7");
    model.result().numerical("int7").setResult();
    model.result().table().create("tbl8", "Table");
    model.result().table("tbl8").comments("\u8868\u9762\u79ef\u5206 8");
    model.result().numerical("int8").set("table", "tbl8");
    model.result().numerical("int8").setResult();
    model.result().table().create("tbl9", "Table");
    model.result().table("tbl9").comments("\u8868\u9762\u79ef\u5206 9");
    model.result().numerical("int9").set("table", "tbl9");
    model.result().numerical("int9").setResult();
    model.result().table().create("tbl10", "Table");
    model.result().table("tbl10").comments("\u8868\u9762\u79ef\u5206 10");
    model.result().numerical("int10").set("table", "tbl10");
    model.result().numerical("int10").setResult();
    model.result().table().create("tbl11", "Table");
    model.result().table("tbl11").comments("\u8868\u9762\u79ef\u5206 11");
    model.result().numerical("int11").set("table", "tbl11");
    model.result().numerical("int11").setResult();
    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").label("\u4e0d\u540c psi \u503c\u7684\u538b\u529b\u5206\u5e03");
    model.result("pg1").set("data", "cln1");
    model.result("pg1").setIndex("looplevelinput", "manual", 0);
    model.result("pg1").setIndex("looplevel", new int[]{16, 17, 18, 19, 20}, 0);
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u4e0d\u540c \\psi \u503c\u7684\u538b\u529b\u5206\u5e03");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "x/a");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "p h<sup>3</sup><sub>t</sub>/(\\eta a<sup>2</sup>v<sub>sq</sub>)");
    model.result("pg1").set("legendpos", "upperleft");
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg1").feature("lngr1").set("linewidth", "preference");
    model.result("pg1").feature("lngr1").set("expr", "p*ht^3/(eta*a^2*sqvel)");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "x/a");
    model.result("pg1").feature("lngr1").set("legend", true);
    model.result("pg1").feature("lngr1").set("legendmethod", "manual");
    model.result("pg1").feature("lngr1").setIndex("legends", "\u6570\u503c\uff1a\\psi=1", 0);
    model.result("pg1").feature("lngr1").setIndex("legends", "\u6570\u503c\uff1a\\psi=0.1", 1);
    model.result("pg1").feature("lngr1").setIndex("legends", "\u6570\u503c\uff1a\\psi=0.01", 2);
    model.result("pg1").feature("lngr1").setIndex("legends", "\u6570\u503c\uff1a\\psi=0.001", 3);
    model.result("pg1").feature("lngr1").setIndex("legends", "\u6570\u503c\uff1a\\psi=1E-4", 4);
    model.result("pg1").create("lngr2", "LineGraph");
    model.result("pg1").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg1").feature("lngr2").set("linewidth", "preference");
    model.result("pg1").feature("lngr2")
         .set("expr", "192*sum(sum(p_summand(m, n, x, y, a, b, psi, 1, 0.02), n, 1, 15), m, 1, 15)/(pi^3)");
    model.result("pg1").feature("lngr2").set("xdata", "expr");
    model.result("pg1").feature("lngr2").set("xdataexpr", "x/a");
    model.result("pg1").feature("lngr2").set("linecolor", "cyclereset");
    model.result("pg1").feature("lngr2").set("linemarker", "cyclereset");
    model.result("pg1").feature("lngr2").set("linestyle", "none");
    model.result("pg1").feature("lngr2").set("markerpos", "interp");
    model.result("pg1").feature("lngr2").set("legend", true);
    model.result("pg1").feature("lngr2").set("legendmethod", "manual");
    model.result("pg1").feature("lngr2").setIndex("legends", "\u89e3\u6790\uff1a\\psi=1", 0);
    model.result("pg1").feature("lngr2").setIndex("legends", "\u89e3\u6790\uff1a\\psi=0.1", 1);
    model.result("pg1").feature("lngr2").setIndex("legends", "\u89e3\u6790\uff1a\\psi=0.01", 2);
    model.result("pg1").feature("lngr2").setIndex("legends", "\u89e3\u6790\uff1a\\psi=0.001", 3);
    model.result("pg1").feature("lngr2").setIndex("legends", "\u89e3\u6790\uff1a\\psi=1E-4", 4);
    model.result("pg1").create("ann1", "Annotation");
    model.result("pg1").feature("ann1").set("text", "k=eval(k) \\\\ H/a=eval(Hbar) \\\\ y/b=0.5");
    model.result("pg1").feature("ann1").set("latexmarkup", true);
    model.result("pg1").feature("ann1").set("posxexpr", 0.175);
    model.result("pg1").feature("ann1").set("posyexpr", 0.85);
    model.result("pg1").feature("ann1").set("exprprecision", 1);
    model.result("pg1").feature("ann1").set("showpoint", false);
    model.result("pg1").feature("ann1").set("showframe", true);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").label("\u4e0d\u540c k \u503c\u7684\u627f\u8f7d\u80fd\u529b");
    model.result("pg2").set("data", "none");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u4e0d\u540c k \u503c\u7684\u627f\u8f7d\u80fd\u529b");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "\\psi");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "W h<sup>3</sup><sub>t</sub>/(\\eta a<sup>3</sup>b v<sub>sq</sub>)");
    model.result("pg2").set("xlog", true);
    model.result("pg2").create("fun1", "Function");
    model.result("pg2").feature("fun1").set("linewidth", "preference");
    model.result("pg2").feature("fun1").set("data", "grid1");
    model.result("pg2").feature("fun1")
         .set("expr", "768*sum(sum(W_summand_psi(m, n, a, psi, 8, 0.02), n, 1, 15), m, 1, 15)/(pi^5)");
    model.result("pg2").feature("fun1").set("xdataexpr", "psi");
    model.result("pg2").feature("fun1").set("lowerbound", "1e-4");
    model.result("pg2").feature("fun1").set("linecolor", "cyclereset");
    model.result("pg2").feature("fun1").set("legend", true);
    model.result("pg2").feature("fun1").set("legendmethod", "manual");
    model.result("pg2").feature("fun1").setIndex("legends", "\u89e3\u6790\uff1ak=8", 0);
    model.result("pg2").feature().duplicate("fun2", "fun1");
    model.result("pg2").feature("fun2")
         .set("expr", "768*sum(sum(W_summand_psi(m, n, a, psi, 4, 0.02), n, 1, 15), m, 1, 15)/(pi^5)");
    model.result("pg2").feature("fun2").set("linecolor", "cycle");
    model.result("pg2").feature("fun2").setIndex("legends", "\u89e3\u6790\uff1ak=4", 0);
    model.result("pg2").feature().duplicate("fun3", "fun2");
    model.result("pg2").feature("fun3")
         .set("expr", "768*sum(sum(W_summand_psi(m, n, a, psi, 2, 0.02), n, 1, 15), m, 1, 15)/(pi^5)");
    model.result("pg2").feature("fun3").setIndex("legends", "\u89e3\u6790\uff1ak=2", 0);
    model.result("pg2").feature().duplicate("fun4", "fun3");
    model.result("pg2").feature("fun4")
         .set("expr", "768*sum(sum(W_summand_psi(m, n, a, psi, 1, 0.02), n, 1, 15), m, 1, 15)/(pi^5)");
    model.result("pg2").feature("fun4").setIndex("legends", "\u89e3\u6790\uff1ak=1", 0);
    model.result("pg2").create("tblp1", "Table");
    model.result("pg2").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg2").feature("tblp1").set("linewidth", "preference");
    model.result("pg2").feature("tblp1").set("xaxisdata", 2);
    model.result("pg2").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg2").feature("tblp1").set("plotcolumns", new int[]{4});
    model.result("pg2").feature("tblp1").set("linestyle", "none");
    model.result("pg2").feature("tblp1").set("linecolor", "cyclereset");
    model.result("pg2").feature("tblp1").set("linemarker", "cyclereset");
    model.result("pg2").feature("tblp1").set("legend", true);
    model.result("pg2").feature("tblp1").set("legendmethod", "manual");
    model.result("pg2").feature("tblp1").setIndex("legends", "\u6570\u503c\uff1ak=8", 0);
    model.result("pg2").feature().duplicate("tblp2", "tblp1");
    model.result("pg2").feature("tblp2").set("table", "tbl2");
    model.result("pg2").feature("tblp2").set("linecolor", "cycle");
    model.result("pg2").feature("tblp2").set("linemarker", "cycle");
    model.result("pg2").feature("tblp2").setIndex("legends", "\u6570\u503c\uff1ak=4", 0);
    model.result("pg2").feature().duplicate("tblp3", "tblp2");
    model.result("pg2").feature("tblp3").set("table", "tbl3");
    model.result("pg2").feature("tblp3").setIndex("legends", "\u6570\u503c\uff1ak=2", 0);
    model.result("pg2").feature().duplicate("tblp4", "tblp3");
    model.result("pg2").feature("tblp4").set("table", "tbl4");
    model.result("pg2").feature("tblp4").setIndex("legends", "\u6570\u503c\uff1ak=1", 0);
    model.result("pg2").create("ann1", "Annotation");
    model.result("pg2").feature("ann1").set("data", "dset2");
    model.result("pg2").feature("ann1").setIndex("looplevel", 1, 0);
    model.result("pg2").feature("ann1").set("text", "H/a=0.02");
    model.result("pg2").feature("ann1").set("latexmarkup", true);
    model.result("pg2").feature("ann1").set("posxexpr", 0.02);
    model.result("pg2").feature("ann1").set("posyexpr", 0.4);
    model.result("pg2").feature("ann1").set("exprprecision", 1);
    model.result("pg2").feature("ann1").set("showpoint", false);
    model.result("pg2").feature("ann1").set("showframe", true);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").label("\u4e0d\u540c psi \u503c\u7684\u627f\u8f7d\u80fd\u529b");
    model.result("pg3").set("data", "none");
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u4e0d\u540c \\psi \u503c\u7684\u627f\u8f7d\u80fd\u529b");
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "k");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "W h<sup>3</sup><sub>t</sub>/(\\eta a<sup>3</sup>b v<sub>sq</sub>)");
    model.result("pg3").set("xlog", true);
    model.result("pg3").create("fun1", "Function");
    model.result("pg3").feature("fun1").set("linewidth", "preference");
    model.result("pg3").feature("fun1").set("data", "grid2");
    model.result("pg3").feature("fun1")
         .set("expr", "768*sum(sum(W_summand_psi(m, n, a, 1, k, 0.02), n, 1, 15), m, 1, 15)/(pi^5)");
    model.result("pg3").feature("fun1").set("xdataexpr", "k");
    model.result("pg3").feature("fun1").set("lowerbound", 1);
    model.result("pg3").feature("fun1").set("upperbound", 8);
    model.result("pg3").feature("fun1").set("linecolor", "cyclereset");
    model.result("pg3").feature("fun1").set("legend", true);
    model.result("pg3").feature("fun1").set("legendmethod", "manual");
    model.result("pg3").feature("fun1").setIndex("legends", "\u89e3\u6790\uff1a\\psi=1", 0);
    model.result("pg3").feature().duplicate("fun2", "fun1");
    model.result("pg3").feature("fun2")
         .set("expr", "768*sum(sum(W_summand_psi(m, n, a, 0.1, k, 0.02), n, 1, 15), m, 1, 15)/(pi^5)");
    model.result("pg3").feature("fun2").set("linecolor", "cycle");
    model.result("pg3").feature("fun2").setIndex("legends", "\u89e3\u6790\uff1a\\psi=0.1", 0);
    model.result("pg3").feature().duplicate("fun3", "fun2");
    model.result("pg3").feature("fun3")
         .set("expr", "768*sum(sum(W_summand_psi(m, n, a, 0.01, k, 0.02), n, 1, 15), m, 1, 15)/(pi^5)");
    model.result("pg3").feature("fun3").setIndex("legends", "\u89e3\u6790\uff1a\\psi=0.01", 0);
    model.result("pg3").feature().duplicate("fun4", "fun3");
    model.result("pg3").feature("fun4")
         .set("expr", "768*sum(sum(W_summand_psi(m, n, a, 0.001, k, 0.02), n, 1, 15), m, 1, 15)/(pi^5)");
    model.result("pg3").feature("fun4").setIndex("legends", "\u89e3\u6790\uff1a\\psi=0.001", 0);
    model.result("pg3").create("tblp1", "Table");
    model.result("pg3").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg3").feature("tblp1").set("linewidth", "preference");
    model.result("pg3").feature("tblp1").set("table", "tbl5");
    model.result("pg3").feature("tblp1").set("xaxisdata", 1);
    model.result("pg3").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg3").feature("tblp1").set("plotcolumns", new int[]{4});
    model.result("pg3").feature("tblp1").set("linestyle", "none");
    model.result("pg3").feature("tblp1").set("linecolor", "cyclereset");
    model.result("pg3").feature("tblp1").set("linemarker", "cyclereset");
    model.result("pg3").feature("tblp1").set("legend", true);
    model.result("pg3").feature("tblp1").set("legendmethod", "manual");
    model.result("pg3").feature("tblp1").setIndex("legends", "\u6570\u503c\uff1a\\psi=1", 0);
    model.result("pg3").feature().duplicate("tblp2", "tblp1");
    model.result("pg3").feature("tblp2").set("table", "tbl6");
    model.result("pg3").feature("tblp2").set("linecolor", "cycle");
    model.result("pg3").feature("tblp2").set("linemarker", "cycle");
    model.result("pg3").feature("tblp2").setIndex("legends", "\u6570\u503c\uff1a\\psi=0.1", 0);
    model.result("pg3").feature().duplicate("tblp3", "tblp2");
    model.result("pg3").feature("tblp3").set("table", "tbl7");
    model.result("pg3").feature("tblp3").setIndex("legends", "\u6570\u503c\uff1a\\psi=0.01", 0);
    model.result("pg3").feature().duplicate("tblp4", "tblp3");
    model.result("pg3").feature("tblp4").set("table", "tbl8");
    model.result("pg3").feature("tblp4").setIndex("legends", "\u6570\u503c\uff1a\\psi=0.001", 0);
    model.result("pg3").create("ann1", "Annotation");
    model.result("pg3").feature("ann1").set("data", "mir2");
    model.result("pg3").feature("ann1").setIndex("looplevel", 1, 0);
    model.result("pg3").feature("ann1").set("text", "a=1 \\\\ H = 0.02");
    model.result("pg3").feature("ann1").set("latexmarkup", true);
    model.result("pg3").feature("ann1").set("posxexpr", 3);
    model.result("pg3").feature("ann1").set("posyexpr", 0.4);
    model.result("pg3").feature("ann1").set("showpoint", false);
    model.result("pg3").feature("ann1").set("showframe", true);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").label("\u4e0d\u540c phi \u503c\u7684\u627f\u8f7d\u80fd\u529b");
    model.result("pg4").set("data", "none");
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "\u4e0d\u540c \\phi \u503c\u7684\u627f\u8f7d\u80fd\u529b");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\\phi");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "W h<sup>3</sup><sub>t</sub>/(\\eta a<sup>3</sup>b v<sub>sq</sub>)");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg4").set("legendpos", "lowerright");
    model.result("pg4").create("fun1", "Function");
    model.result("pg4").feature("fun1").set("linewidth", "preference");
    model.result("pg4").feature("fun1").set("data", "grid3");
    model.result("pg4").feature("fun1")
         .set("expr", "768*sum(sum(W_summand_phi(m, n, a, 5e-9, 1, H), n, 1, 15), m, 1, 15)/(pi^5)");
    model.result("pg4").feature("fun1").set("xdataexpr", "H");
    model.result("pg4").feature("fun1").set("lowerbound", 0.02);
    model.result("pg4").feature("fun1").set("upperbound", 0.1);
    model.result("pg4").feature("fun1").set("linecolor", "cyclereset");
    model.result("pg4").feature("fun1").set("legend", true);
    model.result("pg4").feature("fun1").set("legendmethod", "manual");
    model.result("pg4").feature("fun1").setIndex("legends", "\u89e3\u6790\uff1a\\phi=5E-9", 0);
    model.result("pg4").feature().duplicate("fun2", "fun1");
    model.result("pg4").feature("fun2")
         .set("expr", "768*sum(sum(W_summand_phi(m, n, a, 5e-10, 1, H), n, 1, 15), m, 1, 15)/(pi^5)");
    model.result("pg4").feature("fun2").set("linecolor", "cycle");
    model.result("pg4").feature("fun2").setIndex("legends", "\u89e3\u6790\uff1a\\phi=5E-10", 0);
    model.result("pg4").feature().duplicate("fun3", "fun2");
    model.result("pg4").feature("fun3")
         .set("expr", "768*sum(sum(W_summand_phi(m, n, a, 5e-11, 1, H), n, 1, 15), m, 1, 15)/(pi^5)");
    model.result("pg4").feature("fun3").setIndex("legends", "\u89e3\u6790\uff1a\\phi=5E-11", 0);
    model.result("pg4").create("tblp1", "Table");
    model.result("pg4").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg4").feature("tblp1").set("linewidth", "preference");
    model.result("pg4").feature("tblp1").set("table", "tbl9");
    model.result("pg4").feature("tblp1").set("xaxisdata", 3);
    model.result("pg4").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg4").feature("tblp1").set("plotcolumns", new int[]{4});
    model.result("pg4").feature("tblp1").set("linestyle", "none");
    model.result("pg4").feature("tblp1").set("linecolor", "cyclereset");
    model.result("pg4").feature("tblp1").set("linemarker", "cyclereset");
    model.result("pg4").feature("tblp1").set("legend", true);
    model.result("pg4").feature("tblp1").set("legendmethod", "manual");
    model.result("pg4").feature("tblp1").setIndex("legends", "\u6570\u503c\uff1a\\phi=5E-9", 0);
    model.result("pg4").feature().duplicate("tblp2", "tblp1");
    model.result("pg4").feature("tblp2").set("table", "tbl10");
    model.result("pg4").feature("tblp2").set("linecolor", "cycle");
    model.result("pg4").feature("tblp2").set("linemarker", "cycle");
    model.result("pg4").feature("tblp2").setIndex("legends", "\u6570\u503c\uff1a\\phi=5E-10", 0);
    model.result("pg4").feature().duplicate("tblp3", "tblp2");
    model.result("pg4").feature("tblp3").set("table", "tbl11");
    model.result("pg4").feature("tblp3").setIndex("legends", "\u6570\u503c\uff1a\\phi=5E-11", 0);
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u8fbe\u897f\u538b\u529b\u548c\u901f\u5ea6\u573a");
    model.result("pg5").set("data", "mir2");
    model.result("pg5").setIndex("looplevel", 18, 0);
    model.result("pg5").set("titletype", "manual");
    model.result("pg5")
         .set("title", "\u8868\u9762\uff1a\u8fbe\u897f\u538b\u529b\uff0c\u6d41\u7ebf\uff1a\u8fbe\u897f\u901f\u5ea6\u573a");
    model.result("pg5").set("edges", false);
    model.result("pg5").set("legendpos", "rightdouble");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "p*ht^3/(eta*a^2*sqvel)");
    model.result("pg5").feature("surf1").set("colortable", "GrayScale");
    model.result("pg5").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg5").feature("surf1").create("sel1", "Selection");
    model.result("pg5").feature("surf1").feature("sel1").selection().named("sel1");
    model.result("pg5").create("str1", "Streamline");
    model.result("pg5").feature("str1").setIndex("expr", "dl.u*dl.rho*a/eta", 0);
    model.result("pg5").feature("str1").setIndex("expr", "dl.v*dl.rho*a/eta", 1);
    model.result("pg5").feature("str1").setIndex("expr", "dl.w*dl.rho*a/eta", 2);
    model.result("pg5").feature("str1").set("posmethod", "uniform");
    model.result("pg5").feature("str1").set("udist", 0.05);
    model.result("pg5").feature("str1").set("pointtype", "arrow");
    model.result("pg5").feature("str1").create("col1", "Color");
    model.result("pg5").feature("str1").feature("col1").set("expr", "dl.U*dl.rho*a/eta");
    model.result("pg5").run();

    model.title("\u77e9\u5f62\u591a\u5b54\u677f\u4e0e\u65e0\u5b54\u677f\u4e4b\u95f4\u7684\u6324\u538b\u6cb9\u819c");

    model
         .description("\u672c\u4f8b\u63cf\u8ff0\u5f53\u6d41\u4f53\u7531\u4e8e\u4e24\u4e2a\u77e9\u5f62\u677f\uff08\u5176\u4e2d\u4e00\u4e2a\u5177\u6709\u591a\u5b54\u8868\u9762\uff09\u4e4b\u95f4\u7684\u76f8\u5bf9\u8fd0\u52a8\u800c\u53d7\u5230\u6324\u538b\u65f6\uff0c\u5982\u4f55\u6a21\u62df\u6d41\u4f53\u8584\u819c\u5728\u8fd9\u4e24\u4e2a\u677f\u4e4b\u95f4\u7684\u95f4\u9699\u4e2d\u7684\u6d41\u52a8\uff0c\u5176\u4e2d\u8fd8\u8003\u8651\u4e86\u6d41\u4f53\u5728\u591a\u5b54\u6750\u6599\u4e0e\u8584\u819c\u533a\u57df\u4e4b\u95f4\u7684\u8fdb\u51fa\u60c5\u51b5\u3002");

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
    model.sol("sol29").clearSolutionData();
    model.sol("sol30").clearSolutionData();
    model.sol("sol31").clearSolutionData();
    model.sol("sol32").clearSolutionData();
    model.sol("sol33").clearSolutionData();
    model.sol("sol34").clearSolutionData();
    model.sol("sol35").clearSolutionData();
    model.sol("sol36").clearSolutionData();
    model.sol("sol37").clearSolutionData();

    model.label("squeeze_film_porous.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
