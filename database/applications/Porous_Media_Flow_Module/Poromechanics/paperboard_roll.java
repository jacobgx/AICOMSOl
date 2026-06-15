/*
 * paperboard_roll.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:14 by COMSOL 6.3.0.290. */
public class paperboard_roll {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Porous_Media_Flow_Module\\Poromechanics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics("solid").prop("StructuralTransientBehavior")
         .set("StructuralTransientBehavior", "Quasistatic");
    model.component("comp1").physics().create("mts", "MoistureTransportInSolids", "geom1");

    model.component("comp1").multiphysics().create("unporo1", "UnsaturatedPoroelasticCoupling", 2);
    model.component("comp1").multiphysics("unporo1").set("Solid_physics", "solid");
    model.component("comp1").multiphysics("unporo1").set("Moisture_physics", "mts");
    model.component("comp1").multiphysics("unporo1").selection().all();

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/solid", true);
    model.study("std1").feature("time").setSolveFor("/physics/mts", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/unporo1", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Ri", "0.1[m]", "\u5185\u534a\u5f84");
    model.param().set("Ro", "0.5[m]", "\u5916\u534a\u5f84");
    model.param().set("H", "0.7[m]", "\u9ad8\u5ea6");
    model.param().set("por", "0.5", "\u5b54\u9699\u7387");
    model.param().set("krl", "0", "\u6db2\u4f53\u76f8\u5bf9\u6e17\u900f\u7387");
    model.param().set("krm", "1", "\u6e7f\u7a7a\u6c14\u7684\u76f8\u5bf9\u6e17\u900f\u7387");
    model.param().set("rho_s", "1500[kg/m^3]", "\u7eb8\u677f\u5bc6\u5ea6");
    model.param().set("A", "6.232e3[J/mol]", "\u542b\u6c34\u91cf\u53c2\u6570");
    model.param().set("B", "20.56", "\u542b\u6c34\u91cf\u53c2\u6570");
    model.param()
         .set("tau_zd", "1.2929E-12[s]", "\u84b8\u6c7d\u6269\u6563\u65f6\u95f4\uff08\u5168\u539a\u5ea6\u65b9\u5411\uff09");
    model.param()
         .set("tau_cd", "7.1295E-11[s]", "\u84b8\u6c7d\u6269\u6563\u65f6\u95f4\uff08\u4e0e\u673a\u5668\u65b9\u5411\u5782\u76f4\u7684\u6a2a\u5411\u65b9\u5411\uff09");
    model.param()
         .set("Km_zd", "4.3646E-15[m^2]", "\u6e7f\u7a7a\u6c14\u6e17\u900f\u7387\u53c2\u6570\uff08\u5168\u539a\u5ea6\u65b9\u5411\uff09");
    model.param()
         .set("Km_cd", "1.9405E-13[m^2]", "\u6e7f\u7a7a\u6c14\u6e17\u900f\u7387\u53c2\u6570\uff08\u4e0e\u673a\u5668\u65b9\u5411\u5782\u76f4\u7684\u6a2a\u5411\u65b9\u5411\uff09");
    model.param().set("a_zd", "4.18", "\u6e17\u900f\u7387\u6307\u6570\uff08\u5168\u539a\u5ea6\u65b9\u5411\uff09");
    model.param()
         .set("a_cd", "2.2", "\u6e17\u900f\u7387\u6307\u6570\uff08\u4e0e\u673a\u5668\u65b9\u5411\u5782\u76f4\u7684\u6a2a\u5411\u65b9\u5411\uff09");
    model.param().set("E_md", "3.2[GPa]", "\u6768\u6c0f\u6a21\u91cf\uff08\u673a\u5668\u65b9\u5411\uff09");
    model.param()
         .set("E_cd", "2[GPa]", "\u6768\u6c0f\u6a21\u91cf\uff08\u4e0e\u673a\u5668\u65b9\u5411\u5782\u76f4\u7684\u6a2a\u5411\u65b9\u5411\uff09");
    model.param().set("E_zd", "0.016[GPa]", "\u6768\u6c0f\u6a21\u91cf\uff08\u5168\u539a\u5ea6\u65b9\u5411\uff09");
    model.param().set("nu_mc", "0.34", "\u6cca\u677e\u6bd4");
    model.param().set("nu_mz", "0.01", "\u6cca\u677e\u6bd4");
    model.param().set("nu_cz", "0.01", "\u6cca\u677e\u6bd4");
    model.param().set("G_mc", "1[GPa]", "\u526a\u5207\u6a21\u91cf");
    model.param().set("G_mz", "0.058[GPa]", "\u526a\u5207\u6a21\u91cf");
    model.param().set("G_cz", "0.057[GPa]", "\u526a\u5207\u6a21\u91cf");
    model.param().set("alpha_B", "1", "Biot-Willis \u7cfb\u6570");
    model.param().set("T_amb", "293.15[K]", "\u73af\u5883\u6e29\u5ea6");
    model.param().set("phi_end", "0.85", "\u6700\u7ec8\u73af\u5883\u76f8\u5bf9\u6e7f\u5ea6");
    model.param().set("phi_init", "0.5", "\u521d\u59cb\u73af\u5883\u76f8\u5bf9\u6e7f\u5ea6");
    model.param().set("t_r", "1[day]", "\u589e\u52a0\u76f8\u5bf9\u6e7f\u5ea6\u7684\u65f6\u95f4");
    model.param().set("t_end", "90[day]", "\u4eff\u771f\u65f6\u95f4");
    model.param().label("\u6a21\u578b\u53c2\u6570");

    model.component("comp1").spatialCoord(new String[]{"zd", "phi", "z"});
    model.component("comp1").spatialCoord(new String[]{"zd", "md", "z"});
    model.component("comp1").spatialCoord(new String[]{"zd", "md", "cd"});

    model.component("comp1").materialCoord(new String[]{"ZD", "PHI", "Z"});
    model.component("comp1").materialCoord(new String[]{"ZD", "MD", "Z"});
    model.component("comp1").materialCoord(new String[]{"ZD", "MD", "CD"});

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"Ro-Ri", "H/2"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"Ri", "0"});
    model.component("comp1").geom("geom1").feature("r1").label("\u7eb8\u5f20");
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"Ri", "1.2*H/2"});
    model.component("comp1").geom("geom1").feature("r2").label("\u652f\u67b6");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("createpairs", false);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set(7, 8);
    model.component("comp1").selection("sel1").label("\u9762\u5411\u73af\u5883\u7684\u8fb9\u754c");

    model.component("comp1").func().create("pw1", "Piecewise");
    model.component("comp1").func("pw1").setIndex("pieces", 0, 0, 0);
    model.component("comp1").func("pw1").setIndex("pieces", "t_r", 0, 1);
    model.component("comp1").func("pw1").setIndex("pieces", "phi_init +(phi_end-phi_init)*x/t_r", 0, 2);
    model.component("comp1").func("pw1").setIndex("pieces", "t_r", 1, 0);
    model.component("comp1").func("pw1").setIndex("pieces", "t_end", 1, 1);
    model.component("comp1").func("pw1").setIndex("pieces", "phi_end", 1, 2);
    model.component("comp1").func("pw1").set("argunit", "s");
    model.component("comp1").func("pw1").set("fununit", "1");
    model.component("comp1").func("pw1").set("funcname", "phiw_fun");
    model.component("comp1").func("pw1").label("\u73af\u5883\u76f8\u5bf9\u6e7f\u5ea6");

    model.component("comp1").common().create("ampr1", "AmbientProperties");
    model.component("comp1").common("ampr1").set("T_amb", "T_amb");
    model.component("comp1").common("ampr1").set("phi_amb", "phiw_fun(t)");

    model.component("comp1").material().create("pmat1", "PorousMedia");
    model.component("comp1").material("pmat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("pmat1").propertyGroup("def").func("an1")
         .set("expr", "-(1/B)*log(-R_const*T_amb/A*log(phi))");
    model.component("comp1").material("pmat1").propertyGroup("def").func("an1").set("args", "phi");
    model.component("comp1").material("pmat1").propertyGroup("def").func("an1").set("fununit", "1");
    model.component("comp1").material("pmat1").propertyGroup("def").func("an1").setIndex("argunit", 1, 0);
    model.component("comp1").material("pmat1").propertyGroup("def").func("an1").set("funcname", "wc_int");
    model.component("comp1").material("pmat1").propertyGroup("def").func("an1").label("\u542b\u6c34\u91cf");
    model.component("comp1").material("pmat1").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("pmat1").propertyGroup("def").func("an2")
         .set("expr", "k*(epsilonp*sm/(por*0.9))^a*(1-por*0.9)/(1-epsilonp*sm)");
    model.component("comp1").material("pmat1").propertyGroup("def").func("an2").set("args", "k, a, sm, epsilonp");
    model.component("comp1").material("pmat1").propertyGroup("def").func("an2").set("fununit", "m^2");
    model.component("comp1").material("pmat1").propertyGroup("def").func("an2").setIndex("argunit", "m^2", 0);
    model.component("comp1").material("pmat1").propertyGroup("def").func("an2").setIndex("argunit", 1, 1);
    model.component("comp1").material("pmat1").propertyGroup("def").func("an2").setIndex("argunit", 1, 2);
    model.component("comp1").material("pmat1").propertyGroup("def").func("an2").setIndex("argunit", 1, 3);
    model.component("comp1").material("pmat1").propertyGroup("def").func("an2").set("funcname", "kappa_fun");
    model.component("comp1").material("pmat1").propertyGroup("def").func("an2")
         .label("\u6e7f\u7a7a\u6c14\u6e17\u900f\u7387");
    model.component("comp1").material("pmat1").selection().set(2);
    model.component("comp1").material("pmat1").propertyGroup("def")
         .set("watercontent", new String[]{"wc_int(phi)*rho_s*(1-por)"});
    model.component("comp1").material("pmat1").propertyGroup("def")
         .set("hydraulicpermeability", new String[]{"kappa_fun(Km_zd,a_zd,mts.sm,mts.porosity)", "0", "kappa_fun(Km_cd,a_cd,mts.sm,mts.porosity)"});
    model.component("comp1").material("pmat1").feature().create("solid1", "Solid", "comp1");

    model.component("comp1").physics("mts").selection().set(2);

    model.component("comp1").material("pmat1").feature("solid1").set("vfrac", "1-por");
    model.component("comp1").material("pmat1").propertyGroup("def").addInput("relativehumidity");

    model.component("comp1").physics("mts").feature("pms1").feature("lw1").set("kappa_rl", "krl");
    model.component("comp1").physics("mts").feature("pms1").feature("ma1").set("kappa_rm", "krm");
    model.component("comp1").physics("mts").feature("pms1").feature("ma1")
         .set("Dv", new String[]{"tau_zd*(R_const*T_amb/mts.M_v)", "0", "0", "0", "tau_zd*(R_const*T_amb/mts.M_v)", "0", "0", "0", "tau_cd*(R_const*T_amb/mts.M_v)"});
    model.component("comp1").physics("mts").feature("init1").set("phi_init", "phi_init");
    model.component("comp1").physics("mts").create("mcp1", "MoistureContentAndPressure", 1);
    model.component("comp1").physics("mts").feature("mcp1").selection().named("sel1");
    model.component("comp1").physics("mts").feature("mcp1").set("phi_bc_src", "root.comp1.ampr1.phi_amb");
    model.component("comp1").physics("mts").feature("mcp1").set("pm_bc_src", "root.comp1.ampr1.p_amb");
    model.component("comp1").physics("mts").create("sym1", "Symmetry", 1);
    model.component("comp1").physics("mts").feature("sym1").selection().set(6);
    model.component("comp1").physics("solid").selection().set(2);
    model.component("comp1").physics("solid").feature("lemm1").set("SolidModel", "Orthotropic");
    model.component("comp1").physics("solid").create("roll1", "Roller", 1);
    model.component("comp1").physics("solid").feature("roll1").selection().set(5);
    model.component("comp1").physics("solid").create("symp1", "SymmetryPlane", 1);
    model.component("comp1").physics("solid").feature("symp1").selection().set(6);

    model.component("comp1").material("pmat1").propertyGroup("def").set("density", new String[]{"rho_s"});
    model.component("comp1").material("pmat1").propertyGroup()
         .create("PoroelasticModel", "PoroelasticModel", "Poroelastic_material");
    model.component("comp1").material("pmat1").propertyGroup("PoroelasticModel")
         .set("alphaB", new String[]{"alpha_B"});
    model.component("comp1").material("pmat1").propertyGroup().create("Orthotropic", "Orthotropic", "Orthotropic");
    model.component("comp1").material("pmat1").propertyGroup("Orthotropic")
         .set("nuvector", new String[]{"nu_mz", "nu_mc", "nu_cz"});
    model.component("comp1").material("pmat1").propertyGroup("Orthotropic")
         .set("Gvector", new String[]{"G_mz", "G_mc", "G_cz"});
    model.component("comp1").material("pmat1").propertyGroup("Orthotropic")
         .set("Evector", new String[]{"E_zd", "E_md", "E_cd"});

    model.component("comp1").multiphysics("unporo1").set("pfref_src", "root.comp1.mts.pf_init");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemcount", 30);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(6, 7);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 8);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemcount", 30);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemratio", 8);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(5, 8);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("reverse", true);
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().named("sel1");
    model.component("comp1").mesh("mesh1").create("map2", "Map");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tunit", "d");
    model.study("std1").feature("time").set("tlist", "range(0,t_r/20,t_r) range(t_r,1[d],t_end)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 111, 0);
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
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "w"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().dataset().create("dset1solidrev", "Revolve2D");
    model.result().dataset("dset1solidrev").set("data", "dset1");
    model.result().dataset("dset1solidrev").set("revangle", 225);
    model.result().dataset("dset1solidrev").set("startangle", -90);
    model.result().dataset("dset1solidrev").set("hasspacevars", true);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1solidrev");
    model.result("pg2").setIndex("looplevel", 111, 0);
    model.result("pg2").label("\u5e94\u529b, 3D (solid)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg2").feature("surf1").set("threshold", "manual");
    model.result("pg2").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colortabletrans", "none");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result().dataset("dset1solidrev").set("hasspacevars", true);
    model.result("pg2").feature("surf1").feature("def").set("revcoordsys", "cylindrical");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg2").feature("surf1").feature("def").set("descractive", true);
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u76f8\u5bf9\u6e7f\u5ea6 (mts)");
    model.result("pg3").set("dataisaxisym", "off");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "mts.phi");
    model.result("pg3").feature("surf1").set("colortable", "JupiterAuroraBorealis");
    model.result("pg3").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").label("\u4e8c\u7ef4\u65cb\u8f6c 2");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset1");
    model.result().dataset("rev1").selection().geom("geom1", 2);
    model.result().dataset("rev1").selection().set(2);
    model.result("pg1").run();
    model.result().configuration().create("msol1", "MultiSelectSolution");
    model.result().configuration("msol1").setIndex("looplevelinput", "interp", 0);
    model.result().configuration("msol1").setIndex("interp", "1[d] 10[d] 30[d] 90[d]", 0);
    model.result("pg3").run();
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").create("surf2", "Surface");
    model.result("pg3").feature("surf2").set("expr", "1");
    model.result("pg3").feature("surf2").set("coloring", "uniform");
    model.result("pg3").feature("surf2").set("color", "gray");
    model.result("pg3").feature("surf2").create("sel1", "Selection");
    model.result("pg3").feature("surf2").feature("sel1").selection().set(1);
    model.result("pg3").run();
    model.result("pg3").feature("surf1").create("def1", "Deform");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg3").feature("surf1").feature("def1").set("scale", 1);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").set("solutionparams", "configuration");
    model.result("pg4").label("\u542b\u6c34\u91cf\uff0c\u673a\u5668\u6a2a\u5411");
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u542b\u6c34\u91cf (1)");
    model.result("pg4").set("axislimits", true);
    model.result("pg4").set("xmin", 0);
    model.result("pg4").set("xmax", "H/2");
    model.result("pg4").set("ymin", 0.06);
    model.result("pg4").set("ymax", 0.13);
    model.result("pg4").set("legendpos", "upperleft");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr1").set("linewidth", "preference");
    model.result("pg4").feature("lngr1").selection().set(5);
    model.result("pg4").feature("lngr1").set("expr", "mts.wc/rho_s/(1-por)");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "CD");
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u542b\u6c34\u91cf\uff0c\u5168\u539a\u5ea6");
    model.result("pg5").set("xmin", "Ro/1.1");
    model.result("pg5").set("xmax", "Ro");
    model.result("pg5").run();
    model.result("pg5").feature("lngr1").selection().set(6);
    model.result("pg5").feature("lngr1").set("xdataexpr", "ZD");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").set("data", "dset1");
    model.result("pg6").setIndex("looplevel", 111, 0);
    model.result("pg6").label("\u4f4d\u79fb (solid)");
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").set("showlegends", true);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"solid.disp"});
    model.result("pg6").feature("surf1").set("threshold", "manual");
    model.result("pg6").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg6").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg6").feature("surf1").set("colortabletrans", "none");
    model.result("pg6").feature("surf1").set("colorscalemode", "linear");
    model.result("pg6").feature("surf1").set("resolution", "normal");
    model.result("pg6").feature("surf1").create("def", "Deform");
    model.result("pg6").feature("surf1").feature("def").set("expr", new String[]{"u", "w"});
    model.result("pg6").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg6").label("\u4f4d\u79fb (solid)");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature("surf1").set("unit", "mm");
    model.result("pg6").run();
    model.result().dataset().create("mir1", "Mirror2D");
    model.result().dataset("mir1").setIndex("genpoints", 1, 1, 0);
    model.result().dataset("mir1").setIndex("genpoints", 0, 1, 1);
    model.result().dataset("mir1").set("removesymelem", true);
    model.result().dataset().create("rev2", "Revolve2D");
    model.result().dataset("rev2").set("data", "mir1");
    model.result().dataset("rev2").set("startangle", 180);
    model.result().dataset("rev2").set("revangle", 270);
    model.result().dataset("rev2").label("\u4e8c\u7ef4\u65cb\u8f6c\uff1a\u5168\u7eb8\u677f");
    model.result().dataset("rev2").selection().geom("geom1", 2);
    model.result().dataset("rev2").selection().geom("geom1", 2);
    model.result().dataset("rev2").selection().set(2);
    model.result().dataset().duplicate("rev3", "rev2");
    model.result().dataset("rev3").label("\u4e8c\u7ef4\u65cb\u8f6c\uff1a\u5168\u652f\u67b6");
    model.result().dataset("rev3").set("startangle", 0);
    model.result().dataset("rev3").set("revangle", 360);
    model.result().dataset("rev3").selection().geom("geom1", 2);
    model.result().dataset("rev3").selection().set(1);

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "dataset");
    model.nodeGroup("grp1").placeAfter("dataset", "rev1");
    model.nodeGroup("grp1").add("dataset", "mir1");
    model.nodeGroup("grp1").add("dataset", "rev2");
    model.nodeGroup("grp1").add("dataset", "rev3");
    model.nodeGroup("grp1").label("\u5b8c\u6574\u6a21\u578b");

    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").run();
    model.result("pg7").set("data", "rev2");
    model.result("pg7").label("\u76f8\u5bf9\u6e7f\u5ea6\uff0c\u5b8c\u6574\u6a21\u578b");
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", "mts.phi");
    model.result("pg7").feature("surf1").set("colortable", "JupiterAuroraBorealis");
    model.result("pg7").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg7").run();
    model.result("pg7").feature("surf1").set("rangecoloractive", true);
    model.result("pg7").feature("surf1").set("rangecolormax", 1);
    model.result("pg7").feature("surf1").set("rangecolormin", 0);
    model.result("pg7").feature("surf1").create("def1", "Deform");
    model.result("pg7").feature("surf1").feature("def1").set("revcoordsys", "cylindrical");
    model.result("pg7").run();
    model.result("pg7").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg7").feature("surf1").feature("def1").set("scale", 2);
    model.result("pg7").run();
    model.result("pg7").create("surf2", "Surface");
    model.result("pg7").feature("surf2").set("expr", "1");
    model.result("pg7").feature("surf2").set("data", "rev3");
    model.result("pg7").feature("surf2").create("mtrl1", "MaterialAppearance");
    model.result("pg7").run();
    model.result("pg7").feature("surf2").feature("mtrl1").set("appearance", "custom");
    model.result("pg7").feature("surf2").feature("mtrl1").set("family", "steel");
    model.result("pg7").run();
    model.result("pg7").create("con1", "Contour");
    model.result("pg7").feature("con1").set("expr", "mts.phi");
    model.result("pg7").feature("con1").set("inheritplot", "surf1");
    model.result("pg7").feature("con1").set("colorlegend", false);
    model.result("pg7").feature("con1").set("number", 10);
    model.result("pg7").feature("con1").create("def1", "Deform");
    model.result("pg7").feature("con1").feature("def1").set("revcoordsys", "cylindrical");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").set("view", "new");
    model.result("pg7").run();
    model.result("pg3").run();

    model.title("\u7eb8\u677f\u5377\u4e2d\u7684\u6c34\u5206\u8f93\u9001");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u6a21\u62df\u5177\u6709\u5404\u5411\u5f02\u6027\u6750\u6599\u5c5e\u6027\u7684\u7eb8\u677f\u5377\u5728\u4e0d\u540c\u73af\u5883\u6761\u4ef6\u4e0b\u7684\u6c34\u5206\u8fc1\u79fb\u548c\u8bf1\u5bfc\u81a8\u80c0\u73b0\u8c61\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("paperboard_roll.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
