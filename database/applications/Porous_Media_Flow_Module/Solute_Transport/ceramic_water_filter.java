/*
 * ceramic_water_filter.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:14 by COMSOL 6.3.0.290. */
public class ceramic_water_filter {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Porous_Media_Flow_Module\\Solute_Transport");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics().create("dl", "PorousMediaFlowDarcy", "geom1");
    model.component("comp1").physics().create("tds", "DilutedSpecies", "geom1");
    model.component("comp1").physics("tds").field("concentration").field("c_p");
    model.component("comp1").physics("tds").field("concentration").component(new String[]{"c_p", "c_cl", "c_chcl3"});

    model.component("comp1").multiphysics().create("nsd1", "NavierStokesDarcyCoupling", 2);
    model.component("comp1").multiphysics("nsd1").set("NavierStokes_physics", "spf");
    model.component("comp1").multiphysics("nsd1").set("Darcy_physics", "dl");
    model.component("comp1").multiphysics("nsd1").selection().all();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/dl", true);
    model.study("std1").feature("stat").setSolveFor("/physics/tds", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/nsd1", true);

    model.component("comp1").geom("geom1").insertFile("ceramic_water_filter_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("igv1");

    model.param().label("\u53c2\u6570\uff1a\u51e0\u4f55");
    model.param().create("par2");
    model.param("par2").label("\u53c2\u6570\uff1a\u4f20\u9012");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("M_cl", "35.453[g/mol]", "\u6469\u5c14\u8d28\u91cf\uff0c\u6c2f");
    model.param("par2")
         .set("M_cp", "65[g/mol]", "\u6469\u5c14\u8d28\u91cf\uff0c\u9897\u7c92\uff08\u5e73\u5747\uff09");
    model.param("par2").set("M_chcl3", "119.38[g/mol]", "\u6469\u5c14\u8d28\u91cf\uff0c\u6c2f\u4eff");
    model.param("par2").set("c_cl0", "3[mg/l]/M_cl", "\u521d\u59cb\u6c2f\u6d53\u5ea6");
    model.param("par2").set("c_p0", "250[mg/l]/M_cp", "\u521d\u59cb\u9897\u7c92\u6d53\u5ea6");
    model.param("par2").set("c_chcl30", "60[ug/l]/M_chcl3", "\u521d\u59cb\u6c2f\u4eff\u6d53\u5ea6");
    model.param("par2").set("R_cl", "0.39[1/s]", "\u6c2f\u7684\u53cd\u5e94\u901f\u7387");
    model.param("par2").set("S_cp", "-0.16[1/s]", "\u9897\u7c92\u6c47\u9879");
    model.param("par2").set("Kf", "10", "Freundlich \u5e38\u6570");
    model.param("par2").set("Nf", "2", "Freundlich \u6307\u6570\uff0c\u6c2f\u4eff");
    model.param("par2").set("cref_chcl3", "0.5[mol/m^3]", "\u53c2\u8003\u6d53\u5ea6\uff0c\u6c2f\u4eff");
    model.param("par2").set("por_ceramics", "0.18", "\u5b54\u9699\u7387\uff0c\u9676\u74f7");
    model.param("par2").set("por_carbon", "0.45", "\u5b54\u9699\u7387\uff0c\u6d3b\u6027\u70ad");
    model.param("par2").set("por_fracture", "0.7", "\u5b54\u9699\u7387\uff0c\u88c2\u9699");
    model.param("par2").set("df", "0.2[mm]", "\u88c2\u9699\u5b54\u5f84");
    model.param("par2").set("dp_carbon", "200[um]", "\u9897\u7c92\u78b3\u76f4\u5f84");
    model.param("par2").set("kappa_ceramics", "0.8e-11[m^2]", "\u9676\u74f7\u6e17\u900f\u7387");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("rho", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("cs", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an3", "Analytic");
    model.component("comp1").material("mat1").label("Water, liquid");
    model.component("comp1").material("mat1").set("family", "water");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"273.15", "413.15", "1.3799566804-0.021224019151*T^1+1.3604562827E-4*T^2-4.6454090319E-7*T^3+8.9042735735E-10*T^4-9.0790692686E-13*T^5+3.8457331488E-16*T^6"}, {"413.15", "553.75", "0.00401235783-2.10746715E-5*T^1+3.85772275E-8*T^2-2.39730284E-11*T^3"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"273.15", "553.75", "12010.1471-80.4072879*T^1+0.309866854*T^2-5.38186884E-4*T^3+3.62536437E-7*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").label("Piecewise 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("smooth", "contd1");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("pieces", new String[][]{{"273.15", "293.15", "0.000063092789034*T^3-0.060367639882855*T^2+18.9229382407066*T-950.704055329848"}, {"293.15", "373.15", "0.000010335053319*T^3-0.013395065634452*T^2+4.969288832655160*T+432.257114008512"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").label("Piecewise 4");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"273.15", "553.75", "-0.869083936+0.00894880345*T^1-1.58366345E-5*T^2+7.97543259E-9*T^3"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").label("Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("table", new String[][]{{"273", "1403"}, 
         {"278", "1427"}, 
         {"283", "1447"}, 
         {"293", "1481"}, 
         {"303", "1507"}, 
         {"313", "1526"}, 
         {"323", "1541"}, 
         {"333", "1552"}, 
         {"343", "1555"}, 
         {"353", "1555"}, 
         {"363", "1550"}, 
         {"373", "1543"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("interp", "piecewisecubic");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("fununit", new String[]{"m/s"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("expr", "-1/rho(T)*d(rho(T),T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").label("Analytic 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("funcname", "gamma_w");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("expr", "1+(T/Cp(T))*(alpha_p(T)*cs(T))^2");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("fununit", "1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").label("Analytic 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("funcname", "muB");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("expr", "2.79*eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3")
         .set("plotargs", new String[][]{{"T", "273.15", "553.75"}});
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(T)", "0", "0", "0", "alpha_p(T)", "0", "0", "0", "alpha_p(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("ratioofspecificheat", "gamma_w(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "rho(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material().create("pmat1", "PorousMedia");
    model.component("comp1").material("pmat1").selection().named("geom1_sel2");
    model.component("comp1").material("pmat1").label("\u591a\u5b54\u6750\u6599\uff1a\u9676\u74f7");
    model.component("comp1").material().create("pmat2", "PorousMedia");
    model.component("comp1").material("pmat2").selection().named("geom1_sel1");
    model.component("comp1").material("pmat2").label("\u591a\u5b54\u6750\u6599\uff1a\u70ad");
    model.component("comp1").material().duplicate("mat2", "mat1");
    model.component("comp1").material("mat2").label("\u88c2\u7eb9");
    model.component("comp1").material("mat2").selection().geom("geom1", 2);
    model.component("comp1").material("mat2").selection().named("geom1_csel2_bnd");

    model.component("comp1").physics("spf").selection().named("geom1_difsel1");
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").selection().named("geom1_sel3");
    model.component("comp1").physics("spf").feature("inl1").set("U0in", 0.33);
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").selection().named("geom1_sel4");
    model.component("comp1").physics("dl").selection().named("geom1_csel1_dom");
    model.component("comp1").physics("dl").prop("ShapeProperty").set("order_pressure", 1);
    model.component("comp1").physics("dl").create("porous2", "PorousMedium", 3);
    model.component("comp1").physics("dl").feature("porous2").selection().named("geom1_sel1");
    model.component("comp1").physics("dl").feature("porous2").feature("pm1")
         .set("permeabilityModelType", "kozenyCarman");
    model.component("comp1").physics("dl").feature("porous2").feature("pm1").set("dp", "dp_carbon");
    model.component("comp1").physics("dl").create("frac1", "Fracture", 2);
    model.component("comp1").physics("dl").feature("frac1").selection().named("geom1_csel2_bnd");
    model.component("comp1").physics("dl").feature("frac1").set("df", "df");
    model.component("comp1").physics("dl").feature("frac1").feature("pm1").set("permeabilityModelType", "cubicLaw");
    model.component("comp1").physics("tds").selection().named("geom1_uni1_dom");
    model.component("comp1").physics("tds").prop("TransportMechanism").set("MassTransferInPorousMedia", true);
    model.component("comp1").physics("tds").feature("cdm1").set("u_src", "root.comp1.u");
    model.component("comp1").physics("tds").create("porous1", "PorousMedium", 3);
    model.component("comp1").physics("tds").feature("porous1").selection().named("geom1_sel2");
    model.component("comp1").physics("tds").feature("porous1").feature("fluid1").set("u_src", "root.comp1.dl.u");
    model.component("comp1").physics("tds").feature().duplicate("porous2", "porous1");
    model.component("comp1").physics("tds").feature("porous2").selection().named("geom1_sel1");
    model.component("comp1").physics("tds").feature("porous2").create("ads1", "Adsorptions", 3);
    model.component("comp1").physics("tds").feature("porous2").feature("ads1").set("SorptionType", "Freundlich");
    model.component("comp1").physics("tds").feature("porous2").feature("ads1").setIndex("species", true, 2);
    model.component("comp1").physics("tds").feature("porous2").feature("ads1").setIndex("KF", "Kf", 2);
    model.component("comp1").physics("tds").feature("porous2").feature("ads1").setIndex("NF", "Nf", 2);
    model.component("comp1").physics("tds").feature("porous2").feature("ads1").setIndex("cref", "cref_chcl3", 2);
    model.component("comp1").physics("tds").create("ss1", "SpeciesSource", 3);
    model.component("comp1").physics("tds").feature("ss1").selection().named("geom1_sel2");
    model.component("comp1").physics("tds").feature("ss1").setIndex("S", "S_cp*c_p", 0);
    model.component("comp1").physics("tds").create("reac1", "Reactions", 3);
    model.component("comp1").physics("tds").feature("reac1").selection().named("geom1_sel1");
    model.component("comp1").physics("tds").feature("reac1").setIndex("R_c_cl", "-R_cl*c_cl", 0);
    model.component("comp1").physics("tds").create("fds1", "Fracture", 2);
    model.component("comp1").physics("tds").feature("fds1").selection().named("geom1_csel2_bnd");
    model.component("comp1").physics("tds").feature("fds1").set("d_fr", "df");
    model.component("comp1").physics("tds").feature("fds1").feature("fluid1").set("u_src", "root.comp1.dl.u");
    model.component("comp1").physics("tds").create("init2", "init", 3);
    model.component("comp1").physics("tds").feature("init2").setIndex("initc", "c_p0", 0);
    model.component("comp1").physics("tds").feature("init2").setIndex("initc", "c_cl0", 1);
    model.component("comp1").physics("tds").feature("init2").setIndex("initc", "c_chcl30", 2);
    model.component("comp1").physics("tds").feature("init2").selection().set(1, 3, 4);
    model.component("comp1").physics("tds").create("conc1", "Concentration", 2);
    model.component("comp1").physics("tds").feature("conc1").selection().named("geom1_sel3");
    model.component("comp1").physics("tds").feature("conc1").setIndex("species", true, 0);
    model.component("comp1").physics("tds").feature("conc1").setIndex("c0", "c_p0", 0);
    model.component("comp1").physics("tds").feature("conc1").setIndex("species", true, 1);
    model.component("comp1").physics("tds").feature("conc1").setIndex("c0", "c_cl0", 1);
    model.component("comp1").physics("tds").feature("conc1").setIndex("species", true, 2);
    model.component("comp1").physics("tds").feature("conc1").setIndex("c0", "c_chcl30", 2);
    model.component("comp1").physics("tds").create("out1", "Outflow", 2);
    model.component("comp1").physics("tds").feature("out1").selection().named("geom1_sel4");

    model.component("comp1").material("pmat1").set("porosity", "por_ceramics");
    model.component("comp1").material("pmat1").propertyGroup("def")
         .set("hydraulicpermeability", new String[]{"kappa_ceramics"});
    model.component("comp1").material("pmat1").feature().create("fluid1", "Fluid", "comp1");
    model.component("comp1").material("pmat1").feature("fluid1").set("link", "mat1");
    model.component("comp1").material("pmat2").set("porosity", "por_carbon");
    model.component("comp1").material("pmat2").feature().create("fluid1", "Fluid", "comp1");
    model.component("comp1").material("pmat2").feature().create("solid1", "Solid", "comp1");
    model.component("comp1").material("pmat2").feature("fluid1").set("link", "mat1");
    model.component("comp1").material("pmat2").feature("solid1").set("vfrac", "1-por_carbon");
    model.component("comp1").material("pmat2").feature("solid1").propertyGroup("def")
         .set("density", new String[]{"375"});
    model.component("comp1").material("mat2").propertyGroup("def").set("porosity", new String[]{"por_fracture"});

    model.component("comp1").mesh("mesh1").autoMeshSize(6);
    model.component("comp1").mesh("mesh1").contribute("geom/detail", false);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().named("geom1_csel1_dom");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmax", "th_ceramics/2");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmin", "th_ceramics/4");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hcurveactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hcurve", 0.2);
    model.component("comp1").mesh("mesh1").create("bl2", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl2").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl2").selection().geom(3);
    model.component("comp1").mesh("mesh1").feature("bl2").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl2").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl2").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("bl2").selection().named("geom1_sel2");
    model.component("comp1").mesh("mesh1").feature("bl2").feature("blp").selection()
         .set(31, 32, 33, 34, 51, 52, 71, 72, 78, 85, 93, 97);
    model.component("comp1").mesh("mesh1").feature("bl2").feature("blp").set("blnlayers", 4);
    model.component("comp1").mesh("mesh1").feature("bl2").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl2").feature("blp").set("blhmin", "th_ceramics/20");
    model.component("comp1").mesh("mesh1").create("ftet2", "FreeTet");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").setSolveFor("/physics/tds", false);
    model.study("std1").setGenPlots(false);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/spf", false);
    model.study("std1").feature("time").setSolveFor("/physics/dl", false);
    model.study("std1").feature("time").setSolveFor("/multiphysics/nsd1", false);
    model.study("std1").feature("time").set("tlist", "range(0,0.5,15) range(20,10,180)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");

    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"dl/frac1"});
    model.study("std1").feature("time").set("useadvanceddisable", true);
    model.study("std1").feature("time").set("disabledphysics", new String[]{"tds/fds1"});
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().numerical().create("av1", "AvSurface");
    model.result().numerical("av1").set("intvolume", true);
    model.result().numerical("av1").selection().named("geom1_sel4");
    model.result().numerical("av1").setIndex("expr", "c_p/c_p0", 0);
    model.result().numerical("av1").setIndex("descr", "\u9897\u7c92", 0);
    model.result().numerical("av1").setIndex("expr", "c_cl/c_cl0", 1);
    model.result().numerical("av1").setIndex("descr", "\u6c2f", 1);
    model.result().numerical("av1").setIndex("expr", "c_chcl3/c_chcl30", 2);
    model.result().numerical("av1").setIndex("descr", "\u6c2f\u4eff", 2);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u8868\u9762\u5e73\u5747\u503c 1");
    model.result().numerical("av1").set("table", "tbl1");
    model.result().numerical("av1").setResult();

    model.study("std1").feature("stat").set("useadvanceddisable", false);
    model.study("std1").feature("time").set("useadvanceddisable", false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").selection()
         .set(4, 5, 10, 12, 17, 18, 20, 22, 24, 26, 30, 35, 36, 37, 38, 48, 49, 60, 63, 66, 67, 68, 69, 70, 73, 74, 77, 84, 90, 91, 92, 95, 96, 98, 99, 100, 101, 107, 108, 111, 112);
    model.result().dataset().duplicate("surf2", "surf1");
    model.result().dataset("surf2").selection().set(7, 8, 13, 65, 102);
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u538b\u529b");
    model.result("pg1").set("edges", false);
    model.result("pg1").create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("expr", "p");
    model.result("pg1").feature("mslc1").set("znumber", "0");
    model.result("pg1").feature().duplicate("mslc2", "mslc1");
    model.result("pg1").run();
    model.result("pg1").feature("mslc2").set("expr", "p2");
    model.result("pg1").feature("mslc2").set("inheritplot", "mslc1");
    model.result("pg1").run();
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "p2");
    model.result("pg1").feature("surf1").set("inheritplot", "mslc1");
    model.result("pg1").feature("surf1").create("sel1", "Selection");
    model.result("pg1").feature("surf1").feature("sel1").selection().named("geom1_csel2_bnd");
    model.result("pg1").run();
    model.result("pg1").create("surf2", "Surface");
    model.result("pg1").feature("surf2").set("data", "surf1");
    model.result("pg1").feature("surf2").set("expr", "1");
    model.result("pg1").feature("surf2").set("titletype", "none");
    model.result("pg1").feature("surf2").set("coloring", "uniform");
    model.result("pg1").feature("surf2").set("color", "gray");
    model.result("pg1").feature().duplicate("surf3", "surf2");
    model.result("pg1").run();
    model.result("pg1").feature("surf3").set("data", "surf2");
    model.result("pg1").feature("surf3").set("color", "custom");
    model.result("pg1").feature("surf3")
         .set("customcolor", new double[]{0.07058823853731155, 0.5686274766921997, 0.9529411792755127});
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("\u9897\u7c92\u6d53\u5ea6");
    model.result("pg2").create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("expr", "c_p");
    model.result("pg2").feature("mslc1").set("znumber", "0");
    model.result("pg2").feature("mslc1").set("colortable", "AuroraAustralis");
    model.result("pg2").feature("mslc1").set("recover", "ppr");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "c_p");
    model.result("pg2").feature("surf1").set("inheritplot", "mslc1");
    model.result("pg2").feature("surf1").create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection().named("geom1_csel2_bnd");
    model.result("pg2").run();
    model.result("pg2").create("surf2", "Surface");
    model.result("pg2").feature("surf2").set("data", "surf1");
    model.result("pg2").feature("surf2").set("expr", "1");
    model.result("pg2").feature("surf2").set("titletype", "none");
    model.result("pg2").feature("surf2").set("coloring", "uniform");
    model.result("pg2").feature("surf2").set("color", "gray");
    model.result("pg2").feature().duplicate("surf3", "surf2");
    model.result("pg2").run();
    model.result("pg2").feature("surf3").set("data", "surf2");
    model.result("pg2").feature("surf3").set("color", "custom");
    model.result("pg2").feature("surf3")
         .set("customcolor", new double[]{0.07058823853731155, 0.5686274766921997, 0.9529411792755127});
    model.result("pg2").run();
    model.result("pg2").set("edges", false);
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("\u6c2f\u6d53\u5ea6");
    model.result("pg3").run();
    model.result("pg3").feature("mslc1").set("expr", "c_cl");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("expr", "c_cl");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u6c2f\u4eff\u6d53\u5ea6");
    model.result("pg4").run();
    model.result("pg4").feature("mslc1").set("expr", "c_chcl3");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("expr", "c_chcl3");
    model.result("pg4").run();
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u8868\u9762\u5e73\u5747\u503c 1");
    model.result().numerical("av1").set("table", "tbl2");
    model.result().numerical("av1").setResult();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").set("data", "none");
    model.result("pg5").create("tblp1", "Table");
    model.result("pg5").feature("tblp1").set("source", "table");
    model.result("pg5").feature("tblp1").set("table", "tbl2");
    model.result("pg5").feature("tblp1").set("linewidth", "preference");
    model.result("pg5").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg5").run();
    model.result("pg5").feature("tblp1").set("legend", true);
    model.result("pg5").run();
    model.result("pg5").label("\u968f\u65f6\u95f4\u53d8\u5316\u7684\u6d53\u5ea6");
    model.result("pg5").set("legendpos", "upperleft");
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("tblp2", "tblp1");
    model.result("pg5").run();
    model.result("pg5").feature("tblp2").set("table", "tbl1");
    model.result("pg5").feature("tblp2").set("linestyle", "dotted");
    model.result("pg5").feature("tblp2").set("linecolor", "cyclereset");
    model.result("pg5").feature("tblp2").set("legend", false);
    model.result("pg5").run();
    model.result("pg2").run();

    model.title("\u6d3b\u6027\u70ad\u82af\u9676\u74f7\u6ee4\u6c34\u5668");

    model
         .description("\u672c\u4f8b\u8bf4\u660e\u5982\u4f55\u6a21\u62df\u4e0d\u540c\u6c61\u67d3\u7269\u901a\u8fc7\u5e26\u6709\u6d3b\u6027\u70ad\u82af\u7684\u9676\u74f7\u6ee4\u6c34\u5668\u6ee4\u68d2\u8fdb\u884c\u4f20\u8f93\u3002\u8fd9\u4e9b\u7c7b\u578b\u7684\u6ee4\u6c34\u5668\u53ef\u4ee5\u5728\u53f0\u5f0f\u91cd\u529b\u6ee4\u6c34\u5668\u4e2d\u627e\u5230\uff0c\u4e5f\u53ef\u4ee5\u8fde\u63a5\u5230\u6c34\u9f99\u5934\u6216\u4f5c\u4e3a\u8f83\u5927\u53cd\u6e17\u900f\u8fc7\u6ee4\u7cfb\u7edf\u7684\u4e00\u90e8\u5206\u3002\u672c\u6559\u7a0b\u6f14\u793a\u5982\u4f55\u5efa\u7acb\u6d41\u52a8\u548c\u8f93\u8fd0\u65b9\u7a0b\u6765\u6a21\u62df\u4e0d\u540c\u7684\u8fc7\u6ee4\u673a\u5236\uff0c\u5e76\u68c0\u67e5\u4e86\u9676\u74f7\u96f6\u4ef6\u4e2d\u51fa\u73b0\u7684\u5c0f\u7f1d\u9699\u7684\u5f71\u54cd\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("ceramic_water_filter.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
