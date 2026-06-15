/*
 * under_deposit_corrosion.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:44 by COMSOL 6.3.0.290. */
public class under_deposit_corrosion {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Corrosion_Module\\Galvanic_Corrosion");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("cd", "SecondaryCurrentDistribution", "geom1");

    model.component("comp1").multiphysics().create("ndbdg1", "NonDeformingBoundaryDeformedGeometry", 1);
    model.component("comp1").multiphysics("ndbdg1").set("Echem_physics", "cd");
    model.component("comp1").multiphysics("ndbdg1").selection().all();
    model.component("comp1").multiphysics().create("desdg1", "DeformingElectrodeSurfaceDeformedGeometry", 1);
    model.component("comp1").multiphysics("desdg1").set("Echem_physics", "cd");
    model.component("comp1").multiphysics("desdg1").selection().all();
    model.component("comp1").multiphysics("desdg1").set("embs", "0");

    model.component("comp1").common().create("free1", "DeformingDomainDeformedGeometry");
    model.component("comp1").common("free1").set("smoothingType", "hyperelastic");
    model.component("comp1").common("free1").selection().all();

    model.study().create("std1");
    model.study("std1").create("cdi", "CurrentDistributionInitialization");
    model.study("std1").feature("cdi").set("ftplistmethod", "manual");
    model.study("std1").feature("cdi").set("solnum", "auto");
    model.study("std1").feature("cdi").set("notsolnum", "auto");
    model.study("std1").feature("cdi").set("outputmap", new String[]{});
    model.study("std1").feature("cdi").set("ngenAUX", "1");
    model.study("std1").feature("cdi").set("goalngenAUX", "1");
    model.study("std1").feature("cdi").set("ngenAUX", "1");
    model.study("std1").feature("cdi").set("goalngenAUX", "1");
    model.study("std1").feature("cdi").setSolveFor("/physics/cd", true);
    model.study("std1").feature("cdi").setSolveFor("/multiphysics/ndbdg1", true);
    model.study("std1").feature("cdi").setSolveFor("/multiphysics/desdg1", true);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("ftplistmethod", "manual");
    model.study("std1").feature("time").set("initialtime", "0");
    model.study("std1").feature("time").set("solnum", "auto");
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").set("outputmap", new String[]{});
    model.study("std1").feature("time").setSolveFor("/physics/cd", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/ndbdg1", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/desdg1", true);

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new double[]{0.01, 0.01});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new double[]{-0.01, 0});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"0.01", "0.01+1e-4"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"0", "-1e-4"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("r1", "r2");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("fin");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("sigma", "2.5[S/m]", "\u7535\u89e3\u8d28\u7535\u5bfc\u7387");
    model.param().set("rho_Mg", "1820[kg/m^3]", "\u9541\u7684\u5bc6\u5ea6");
    model.param().set("M_Mg", "0.025[kg/mol]", "\u9541\u7684\u5206\u5b50\u91cf");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("LocalCurrentDensity", "LocalCurrentDensity", "Local current density");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElectrodePotential", "ElectrodePotential", "Equilibrium potential");
    model.component("comp1").material("mat1").label("Mild steel in 1.6 wt% NaCl");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").label("Local current density");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").func("int1")
         .set("funcname", "iloc_exp");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").func("int1")
         .set("table", new String[][]{{"-1.724995712", "-1294.81311"}, 
         {"-1.698596505", "-1221.153151"}, 
         {"-1.662264475", "-980.7098549"}, 
         {"-1.596238566", "-753.7166809"}, 
         {"-1.543398316", "-562.6316733"}, 
         {"-1.454159793", "-255.5659935"}, 
         {"-1.374861066", "-140.365249"}, 
         {"-1.305467272", "-80.5505944"}, 
         {"-1.239329798", "-38.79504171"}, 
         {"-1.173136541", "-14.79120785"}, 
         {"-1.120125456", "-5.398051399"}, 
         {"-1.060525028", "-2.02833023"}, 
         {"-0.991033613", "-0.773317276"}, 
         {"-0.941390414", "-0.377963209"}, 
         {"-0.891736755", "-0.176812853"}, 
         {"-0.805949793", "-0.152711324"}, 
         {"-0.789511301", "-0.190093365"}, 
         {"-0.740063341", "-0.210493637"}, 
         {"-0.690584004", "-0.204375322"}, 
         {"-0.660862235", "-0.174014568"}, 
         {"-0.644333096", "-0.148175442"}, 
         {"-0.614660136", "-0.154784845"}, 
         {"-0.594801462", "-0.115565489"}, 
         {"-0.584725695", "-0.054074545"}, 
         {"-0.584324756", "-0.010083353"}, 
         {"-0.580789522", "-0.003735135"}, 
         {"-0.580548958", "-0.001363558"}, 
         {"-0.568684656", "0.35574106"}, 
         {"-0.559103961", "1.324140938"}, 
         {"-0.552821424", "4.928818185"}, 
         {"-0.543167514", "13.50051829"}, 
         {"-0.533513605", "36.97924882"}, 
         {"-0.513954763", "96.94225444"}, 
         {"-0.448155472", "192.5056295"}, 
         {"-0.402141641", "376.7744582"}, 
         {"-0.366011823", "705.8575518"}, 
         {"-0.32981925", "1016.687802"}});
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").func("int1")
         .set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").func("int1")
         .set("fununit", new String[]{"A/m^2"});
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").func("int1")
         .set("argunit", new String[]{"V"});
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity")
         .set("ilocmat", "iloc_exp(E_vs_ref_exp)");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity")
         .setPropertyInfo("ilocmat", "K. B. Deshpande, \u201cEffect of aluminium spacer on galvanic corrosion between\nmagnesium and mild steel using numerical model and SVET experiments,\u201d Corrosion\nScience, vol. 62, p. 184, 2012.");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").set("E_vs_SHE", "V");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity")
         .descr("E_vs_SHE", "\u7535\u6781\u7535\u4f4d vs. SHE");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity")
         .set("E_ref_exp_vs_SHE", "0.241 [V]");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity")
         .descr("E_ref_exp_vs_SHE", "\u5b9e\u9a8c\u4e2d\u7684\u53c2\u6bd4\u7535\u6781\u7535\u4f4d vs. SHE");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity")
         .set("E_vs_ref_exp", "E_vs_SHE-E_ref_exp_vs_SHE");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity")
         .descr("E_vs_ref_exp", "\u7535\u6781\u7535\u4f4d vs. \u5b9e\u9a8c SCE \u53c2\u6bd4\u7535\u6781");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").addInput("electricpotential");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").label("Equilibrium potential");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").set("Eeq", "Eeq_vs_SHE");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential")
         .setPropertyInfo("Eeq", "K. B. Deshpande, \u201cEffect of aluminium spacer on galvanic corrosion between\nmagnesium and mild steel using numerical model and SVET experiments,\u201d Corrosion\nScience, vol. 62, p. 184, 2012.");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").set("dEeqdT", "0[V/K]");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").set("cEeqref", "0[mol/m^3]");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").set("Eeq_vs_ref_exp", "-0.58 [V]");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential")
         .descr("Eeq_vs_ref_exp", "\u5e73\u8861\uff08\u5f00\u8def\uff09\u7535\u4f4d vs. SCE");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential")
         .set("E_ref_exp_vs_SHE", "0.241 [V]");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential")
         .descr("E_ref_exp_vs_SHE", "\u5b9e\u9a8c\u4e2d\u7684\u53c2\u6bd4\u7535\u6781\u7535\u4f4d vs. SHE");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential")
         .set("Eeq_vs_SHE", "Eeq_vs_ref_exp+E_ref_exp_vs_SHE");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential")
         .descr("Eeq_vs_SHE", "\u5e73\u8861\uff08\u5f00\u8def\uff09\u7535\u4f4d vs. SHE");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").addInput("concentration");
    model.component("comp1").material("mat1").selection().geom("geom1", 1);
    model.component("comp1").material("mat1").selection().set(2, 4);
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("LocalCurrentDensity", "LocalCurrentDensity", "Local current density");
    model.component("comp1").material("mat2").propertyGroup("LocalCurrentDensity").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup()
         .create("ElectrodePotential", "ElectrodePotential", "Equilibrium potential");
    model.component("comp1").material("mat2").label("AE44 in 1.6 wt% NaCl");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("LocalCurrentDensity").label("Local current density");
    model.component("comp1").material("mat2").propertyGroup("LocalCurrentDensity").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("LocalCurrentDensity").func("int1")
         .set("funcname", "iloc_exp");
    model.component("comp1").material("mat2").propertyGroup("LocalCurrentDensity").func("int1")
         .set("table", new String[][]{{"-2.13672391", "-219.0812174"}, 
         {"-2.093791281", "-125.7315703"}, 
         {"-2.067371202", "-82.29708529"}, 
         {"-2.001321004", "-58.81938165"}, 
         {"-1.945178336", "-40.23311643"}, 
         {"-1.885733157", "-26.3395082"}, 
         {"-1.849405548", "-17.49533024"}, 
         {"-1.80317041", "-10.3387525"}, 
         {"-1.760237781", "-5.678841057"}, 
         {"-1.727212682", "-3.405033309"}, 
         {"-1.697490092", "-2.164569096"}, 
         {"-1.674372523", "-1.316913195"}, 
         {"-1.654557464", "-0.824959959"}, 
         {"-1.631439894", "-0.487438864"}, 
         {"-1.608322325", "-0.314414578"}, 
         {"-1.571994716", "-0.170187739"}, 
         {"-1.562087186", "-0.103533385"}, 
         {"-1.555482166", "-0.041218975"}, 
         {"-1.545574637", "-0.01570633"}, 
         {"-1.542272127", "0.040623892"}, 
         {"-1.542272127", "0.102042604"}, 
         {"-1.542272127", "0.271755509"}, 
         {"-1.542272127", "0.572780046"}, 
         {"-1.532364597", "1.155513296"}, 
         {"-1.525759577", "2.297229688"}, 
         {"-1.515852048", "4.702628735"}, 
         {"-1.509247028", "9.486791868"}, 
         {"-1.489431968", "14.92777544"}, 
         {"-1.476221929", "25.26969914"}, 
         {"-1.456406869", "40.34827744"}, 
         {"-1.403566711", "63.50158896"}, 
         {"-1.354029062", "85.09313601"}, 
         {"-1.301188904", "105.9904709"}, 
         {"-1.261558785", "120.9233936"}, 
         {"-1.202113606", "148.4397409"}, 
         {"-1.149273448", "198.9154478"}, 
         {"-1.099735799", "247.7606987"}, 
         {"-1.050198151", "286.8469856"}});
    model.component("comp1").material("mat2").propertyGroup("LocalCurrentDensity").func("int1")
         .set("extrap", "linear");
    model.component("comp1").material("mat2").propertyGroup("LocalCurrentDensity").func("int1")
         .set("fununit", new String[]{"A/m^2"});
    model.component("comp1").material("mat2").propertyGroup("LocalCurrentDensity").func("int1")
         .set("argunit", new String[]{"V"});
    model.component("comp1").material("mat2").propertyGroup("LocalCurrentDensity")
         .set("ilocmat", "iloc_exp(E_vs_ref_exp)");
    model.component("comp1").material("mat2").propertyGroup("LocalCurrentDensity")
         .setPropertyInfo("ilocmat", "K. B. Deshpande, \u201cEffect of aluminium spacer on galvanic corrosion between\nmagnesium and mild steel using numerical model and SVET experiments\u201d, Corrosion\nScience, vol. 62, p. 184, 2012.");
    model.component("comp1").material("mat2").propertyGroup("LocalCurrentDensity").set("E_vs_SHE", "V");
    model.component("comp1").material("mat2").propertyGroup("LocalCurrentDensity")
         .descr("E_vs_SHE", "\u7535\u6781\u7535\u4f4d vs. SHE");
    model.component("comp1").material("mat2").propertyGroup("LocalCurrentDensity")
         .set("E_ref_exp_vs_SHE", "0.241 [V]");
    model.component("comp1").material("mat2").propertyGroup("LocalCurrentDensity")
         .descr("E_ref_exp_vs_SHE", "\u5b9e\u9a8c\u4e2d\u7684\u53c2\u6bd4\u7535\u6781\u7535\u4f4d vs. SHE");
    model.component("comp1").material("mat2").propertyGroup("LocalCurrentDensity")
         .set("E_vs_ref_exp", "E_vs_SHE-E_ref_exp_vs_SHE");
    model.component("comp1").material("mat2").propertyGroup("LocalCurrentDensity")
         .descr("E_vs_ref_exp", "\u7535\u6781\u7535\u4f4d vs. \u5b9e\u9a8c SCE \u53c2\u6bd4\u7535\u6781");
    model.component("comp1").material("mat2").propertyGroup("LocalCurrentDensity").addInput("electricpotential");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").label("Equilibrium potential");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").set("Eeq", "Eeq_vs_SHE");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential")
         .setPropertyInfo("Eeq", "K. B. Deshpande, \u201cEffect of aluminium spacer on galvanic corrosion between\nmagnesium and mild steel using numerical model and SVET experiments\u201d, Corrosion\nScience, vol. 62, p. 184, 2012.");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").set("dEeqdT", "0[V/K]");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").set("cEeqref", "0[mol/m^3]");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").set("Eeq_vs_ref_exp", "-1.55 [V]");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential")
         .descr("Eeq_vs_ref_exp", "\u5e73\u8861\uff08\u5f00\u8def\uff09\u7535\u4f4d vs. SCE");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential")
         .set("E_ref_exp_vs_SHE", "0.241 [V]");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential")
         .descr("E_ref_exp_vs_SHE", "\u5b9e\u9a8c\u4e2d\u7684\u53c2\u6bd4\u7535\u6781\u7535\u4f4d vs. SHE");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential")
         .set("Eeq_vs_SHE", "Eeq_vs_ref_exp+E_ref_exp_vs_SHE");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential")
         .descr("Eeq_vs_SHE", "\u5e73\u8861\uff08\u5f00\u8def\uff09\u7535\u4f4d vs. SHE");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").addInput("concentration");
    model.component("comp1").material("mat2").selection().geom("geom1", 1);
    model.component("comp1").material("mat2").selection().set(5);

    model.component("comp1").physics("cd").prop("PhysicsVsMaterialsReferenceElectrodePotential")
         .set("PhysicsVsMaterialsReferenceElectrodePotential", "SCE");
    model.component("comp1").physics("cd").feature("ice1").set("sigmal_mat", "userdef");
    model.component("comp1").physics("cd").feature("ice1")
         .set("sigmal", new String[]{"sigma", "0", "0", "0", "sigma", "0", "0", "0", "sigma"});
    model.component("comp1").physics("cd").create("es1", "ElectrodeSurface", 1);
    model.component("comp1").physics("cd").feature("es1").selection().set(2, 4);
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("ilocmat_mat", "from_mat");
    model.component("comp1").physics("cd").create("es2", "ElectrodeSurface", 1);
    model.component("comp1").physics("cd").feature("es2").selection().set(5);
    model.component("comp1").physics("cd").feature("es2").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("cd").feature("es2").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("cd").feature("es2").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("cd").feature("es2").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("cd").feature("es2").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("cd").feature("es2").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("cd").feature("es2").setIndex("Species", "Mg", 0, 0);
    model.component("comp1").physics("cd").feature("es2").setIndex("rhos", "rho_Mg", 0, 0);
    model.component("comp1").physics("cd").feature("es2").setIndex("Ms", "M_Mg", 0, 0);
    model.component("comp1").physics("cd").feature("es2").feature("er1").set("nm", 2);
    model.component("comp1").physics("cd").feature("es2").feature("er1").setIndex("Vib", 1, 0, 0);
    model.component("comp1").physics("cd").feature("es2").feature("er1").set("ilocmat_mat", "from_mat");

    model.component("comp1").multiphysics("ndbdg1").set("BoundaryCondition", "ZeroNormalDisplacement");
    model.component("comp1").multiphysics("desdg1").selection().set(5);

    model.study("std1").feature("time").set("tlist", "range(0, 12*3600, 3*24*3600)");
    model.study("std1").feature("time").set("autoremesh", true);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").label("\u91cd\u65b0\u5212\u5206\u7f51\u683c\u540e\u7684\u89e3 1");
    model.sol("sol3").study("std1");
    model.sol("sol1").feature("t1").feature("arDef").set("tadapsol", "sol3");
    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset3");
    model.result("pg1").setIndex("looplevel", 13, 0);
    model.result("pg1").label("\u7535\u89e3\u8d28\u7535\u4f4d (cd)");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"phil"});
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("expr", new String[]{"cd.Ilx", "cd.Ily"});
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("recover", "pprint");
    model.result("pg1").feature("str1").set("pointtype", "arrow");
    model.result("pg1").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg1").feature("str1").set("color", "gray");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").setIndex("looplevel", 13, 0);
    model.result("pg2").label("\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6 (cd)");
    model.result("pg2").create("str1", "Streamline");
    model.result("pg2").feature("str1").set("expr", new String[]{"cd.Ilx", "cd.Ily"});
    model.result("pg2").feature("str1").set("posmethod", "uniform");
    model.result("pg2").feature("str1").set("recover", "pprint");
    model.result("pg2").feature("str1").set("pointtype", "arrow");
    model.result("pg2").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg2").feature("str1").set("color", "gray");
    model.result("pg2").feature("str1").create("col1", "Color");
    model.result("pg2").feature("str1").feature("col1").set("expr", "root.comp1.cd.IlMag");
    model.result("pg2").create("line1", "Line");
    model.result("pg2").feature("line1").set("expr", new String[]{"abs(cd.itot)"});
    model.result("pg2").feature("line1").set("linetype", "tube");
    model.result("pg2").feature("line1").set("inherittubescale", false);
    model.result("pg2").feature("line1").set("inheritplot", "str1");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").setIndex("looplevel", 13, 0);
    model.result("pg3").label("\u7535\u6781\u7535\u4f4d vs. \u76f8\u90bb\u53c2\u6bd4\u7535\u4f4d (cd)");
    model.result("pg3").create("str1", "Streamline");
    model.result("pg3").feature("str1").set("expr", new String[]{"cd.Ilx", "cd.Ily"});
    model.result("pg3").feature("str1").set("posmethod", "uniform");
    model.result("pg3").feature("str1").set("recover", "pprint");
    model.result("pg3").feature("str1").set("pointtype", "arrow");
    model.result("pg3").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg3").feature("str1").set("color", "gray");
    model.result("pg3").create("line1", "Line");
    model.result("pg3").feature("line1").set("expr", new String[]{"cd.Evsref"});
    model.result("pg3").feature("line1").set("linetype", "tube");
    model.result("pg3").feature("line1").set("inherittubescale", false);
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").setIndex("looplevel", 13, 0);
    model.result("pg4").label("\u7535\u6781\u603b\u539a\u5ea6\u53d8\u5316 (cd)");
    model.result("pg4").create("line1", "Line");
    model.result("pg4").feature("line1").set("expr", new String[]{"cd.sbtot"});
    model.result("pg4").feature("line1").set("linetype", "tube");
    model.result("pg4").feature("line1").set("inherittubescale", false);
    model.result("pg4").feature("line1").set("unit", "\u00b5m");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").setIndex("looplevel", 13, 0);
    model.result("pg5").label("\u53d8\u5f62\u51e0\u4f55");
    model.result("pg5").create("mesh1", "Mesh");
    model.result("pg5").feature("mesh1").set("meshdomain", "surface");
    model.result("pg5").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg5").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg5").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg5").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg5").feature("mesh1").feature("sel1").selection().set(1);
    model.result("pg5").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg5").feature("mesh1").set("qualexpr", "comp1.material.relVol");
    model.result("pg5").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg1").run();
    model.result("pg1").set("edges", false);
    model.result("pg1").create("line1", "Line");
    model.result("pg1").feature("line1").set("expr", "1");
    model.result("pg1").feature("line1").set("coloring", "uniform");
    model.result("pg1").feature("line1").set("color", "black");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{1});
    model.result("pg1").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u5c40\u90e8\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr1").set("linewidth", "preference");
    model.result("pg6").feature("lngr1").set("data", "dset3");
    model.result("pg6").feature("lngr1").setIndex("looplevelinput", "first", 0);
    model.result("pg6").feature("lngr1").selection().set(2, 4, 5);
    model.result("pg6").feature("lngr1").set("expr", "cd.iloc_er1");
    model.result("pg6").feature("lngr1").set("descr", "\u5c40\u90e8\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg6").feature("lngr1").set("xdata", "expr");
    model.result("pg6").feature("lngr1").set("xdataexpr", "x");
    model.result("pg6").feature("lngr1").set("legend", true);
    model.result("pg6").feature("lngr1").set("legendmethod", "manual");
    model.result("pg6").feature("lngr1").setIndex("legends", "t=0 h", 0);
    model.result("pg6").feature().duplicate("lngr2", "lngr1");
    model.result("pg6").run();
    model.result("pg6").feature("lngr2").setIndex("looplevelinput", "last", 0);
    model.result("pg6").feature("lngr2").set("titletype", "none");
    model.result("pg6").feature("lngr2").setIndex("legends", "t=72 h", 0);
    model.result("pg6").run();

    model.title("\u7535\u5076\u8150\u8680\u9020\u6210\u7535\u6781\u53d8\u5f62");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u6a21\u62df\u9633\u6781\u8150\u8680\u5f15\u8d77\u51e0\u4f55\u53d8\u5f62\u7684\u7535\u5076\u3002\n\n\u6a21\u578b\u4f7f\u7528\u9541\u5408\u91d1 (AE44) \u7684\u53c2\u6570\u6570\u636e\uff0c\u8fd9\u662f\u4e00\u79cd\u76d0\u6eb6\u6db2\uff08\u76d0\u6c34\uff09\u4e2d\u7684\u4f4e\u78b3\u94a2\u7535\u8026\u3002\n\n\u672c\u4f8b\u4e2d\u4e3a\u4e8c\u7ef4\u6a21\u578b\u3002");

    model.label("galvanic_corrosion_with_deformation.mph");

    model.result("pg6").run();

    model.component("comp1").physics().create("tds", "DilutedSpecies", "geom1");

    model.study("std1").feature("cdi").setSolveFor("/physics/tds", true);
    model.study("std1").feature("time").setSolveFor("/physics/tds", true);

    model.component("comp1").physics("tds").field("concentration").field("cMg");
    model.component("comp1").physics("tds").field("concentration").component(new String[]{"cMg", "cOH"});
    model.component("comp1").physics("tds").selection().set();
    model.component("comp1").physics().create("ls", "LevelSet", "geom1");

    model.study("std1").feature("cdi").setSolveFor("/physics/ls", true);
    model.study("std1").feature("time").setSolveFor("/physics/ls", true);

    model.component("comp1").physics("ls").selection().set();

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("DMg", "7.05e-10[m^2/s]", "Mg \u79bb\u5b50\u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("DOH", "5.27e-9[m^2/s]", "OH \u79bb\u5b50\u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("cOH0", "1e-4[mol/m^3]", "OH \u79bb\u5b50\u7684\u521d\u59cb\u6d53\u5ea6");
    model.param().set("cMg0", "0[mol/m^3]", "Mg \u79bb\u5b50\u7684\u521d\u59cb\u6d53\u5ea6");
    model.param().set("ksp", "0.45[mol^3/m^9]", "Mg(OH)2 \u7684\u6eb6\u5ea6\u79ef\u5e38\u6570");
    model.param().set("k", "3.7e-7[m^7/mol^2/s]", "Mg(OH)2 \u7684\u6c89\u6dc0\u53cd\u5e94\u901f\u7387\u5e38\u6570");
    model.param().set("Tau", "1", "\u6709\u6548\u6269\u6563\u7cfb\u6570\u56e0\u5b50");
    model.param().set("sL", "1", "\u6d41\u4f53\u9971\u548c\u5ea6");
    model.param().set("m", "1", "\u80f6\u7ed3\u6307\u6570");
    model.param().set("n", "2", "\u9971\u548c\u7cfb\u6570");
    model.param().set("M_MgOH2", "0.058[kg/mol]", "Mg(OH)2 \u7684\u5206\u5b50\u91cf");
    model.param().set("rho_MgOH2", "2450[kg/m^3]", "Mg(OH)2 \u7684\u5bc6\u5ea6");
    model.param().set("epsilon", "0.55", "\u8150\u8680\u4ea7\u7269\u6c89\u79ef\u7269\u7684\u5b54\u9699\u7387");
    model.param().set("N_Mac", "Tau/epsilon", "MacMullin \u6570");
    model.param()
         .set("DMge", "DMg*(1-epsilon)+DMg*epsilon/N_Mac", "Mg \u79bb\u5b50\u7684\u6709\u6548\u6269\u6563\u7cfb\u6570");
    model.param()
         .set("DOHe", "DOH*(1-epsilon)+DOH*epsilon/N_Mac", "OH \u79bb\u5b50\u7684\u6709\u6548\u6269\u6563\u7cfb\u6570");
    model.param().set("sigmae", "sigma*sL^m*epsilon^n", "\u6709\u6548\u7535\u89e3\u8d28\u7535\u5bfc\u7387");

    model.component("comp1").func().create("step1", "Step");
    model.component("comp1").func("step1").set("smooth", 0.001);

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("step_variable", "(max(cMg,eps)*max(cOH,eps)^2/ksp)-1", "\u6c89\u6dc0\u53cd\u5e94\u7684\u9636\u8dc3\u53d8\u91cf");
    model.component("comp1").variable("var1")
         .set("R_MgOH2", "k*(max(cMg,eps)*(max(cOH,eps))^2-ksp)*step1(step_variable)", "\u6c89\u6dc0\u53cd\u5e94\u901f\u7387");
    model.component("comp1").variable("var1")
         .set("R_Mg", "R_MgOH2", "Mg \u79bb\u5b50\u7684\u53cd\u5e94\u901f\u7387");
    model.component("comp1").variable("var1")
         .set("R_OH", "2*R_MgOH2", "OH \u79bb\u5b50\u7684\u53cd\u5e94\u901f\u7387");
    model.component("comp1").variable("var1")
         .set("vn_corprod", "R_MgOH2*M_MgOH2/rho_MgOH2", "\u8150\u8680\u4ea7\u7269\u901f\u5ea6");
    model.component("comp1").variable("var1")
         .set("u_corprod", "vn_corprod*ls.intnormx*step1(0.6-phils)", "\u8150\u8680\u4ea7\u7269\u901f\u5ea6\uff0cx \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("v_corprod", "max(vn_corprod*ls.intnormy+genext1(cd.vn*cd.ny),eps)*step1(0.6-phils)", "\u8150\u8680\u4ea7\u7269\u901f\u5ea6\uff0cy \u5206\u91cf");

    model.component("comp1").cpl().create("genext1", "GeneralExtrusion");
    model.component("comp1").cpl("genext1").selection().geom("geom1", 1);
    model.component("comp1").cpl("genext1").selection().set(5);
    model.component("comp1").cpl("genext1").set("dstmap", new String[]{"x", ""});
    model.component("comp1").cpl("genext1").set("srcframe", "mesh");
    model.component("comp1").cpl("genext1").set("usesrcmap", true);
    model.component("comp1").cpl("genext1").set("srcmap", new String[]{"x", ""});
    model.component("comp1").cpl("genext1").set("method", "closest");

    model.component("comp1").physics("tds").prop("TransportMechanism").set("Convection", false);
    model.component("comp1").physics("tds").prop("TransportMechanism").set("Migration", true);
    model.component("comp1").physics("tds").selection().set(1);
    model.component("comp1").physics("tds").feature("sp1").setIndex("z", 2, 0);
    model.component("comp1").physics("tds").feature("sp1").setIndex("z", -1, 1);
    model.component("comp1").physics("tds").feature("cdm1")
         .set("D_cMg", new String[]{"DMg*ls.Vf2+DMge*ls.Vf1", "0", "0", "0", "DMg*ls.Vf2+DMge*ls.Vf1", "0", "0", "0", "DMg*ls.Vf2+DMge*ls.Vf1"});
    model.component("comp1").physics("tds").feature("cdm1")
         .set("D_cOH", new String[]{"DOH*ls.Vf2+DOHe*ls.Vf1", "0", "0", "0", "DOH*ls.Vf2+DOHe*ls.Vf1", "0", "0", "0", "DOH*ls.Vf2+DOHe*ls.Vf1"});
    model.component("comp1").physics("tds").feature("cdm1").set("V", "phil");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("tds").feature("init1").setIndex("initc", "cOH0", 1);
    model.component("comp1").physics("tds").create("reac1", "Reactions", 2);
    model.component("comp1").physics("tds").feature("reac1").selection().set(1);
    model.component("comp1").physics("tds").feature("reac1").setIndex("R_cMg", "-R_Mg*ls.delta", 0);
    model.component("comp1").physics("tds").feature("reac1").setIndex("R_cOH", "-R_OH*ls.delta", 0);
    model.component("comp1").physics("tds").create("conc1", "Concentration", 1);
    model.component("comp1").physics("tds").feature("conc1").selection().set(3, 6);
    model.component("comp1").physics("tds").feature("conc1").setIndex("species", true, 1);
    model.component("comp1").physics("tds").feature("conc1").setIndex("c0", "cOH0", 1);
    model.component("comp1").physics("tds").create("eeic1", "ElectrodeElectrolyteInterfaceCoupling", 1);
    model.component("comp1").physics("tds").feature("eeic1").selection().set(2, 4);
    model.component("comp1").physics("tds").feature("eeic1").feature("rc1")
         .set("iloc_src", "root.comp1.cd.es1.er1.iloc");
    model.component("comp1").physics("tds").feature("eeic1").feature("rc1").set("nm", 4);
    model.component("comp1").physics("tds").feature("eeic1").feature("rc1").setIndex("Vib", 4, 1);
    model.component("comp1").physics("tds").create("eeic2", "ElectrodeElectrolyteInterfaceCoupling", 1);
    model.component("comp1").physics("tds").feature("eeic2").selection().set(5);
    model.component("comp1").physics("tds").feature("eeic2").feature("rc1")
         .set("iloc_src", "root.comp1.cd.es2.er1.iloc");
    model.component("comp1").physics("tds").feature("eeic2").feature("rc1").set("nm", 2);
    model.component("comp1").physics("tds").feature("eeic2").feature("rc1").setIndex("Vib", -1, 0);
    model.component("comp1").physics("ls").selection().set(1);
    model.component("comp1").physics("ls").feature("lsm1").set("gamma", "max(vn_corprod,eps)");
    model.component("comp1").physics("ls").feature("lsm1").set("epsilon_ls", "ls.hmax/4");
    model.component("comp1").physics("ls").feature("lsm1").set("u", new String[]{"u_corprod", "v_corprod", "0"});
    model.component("comp1").physics("ls").feature("init1").set("FluidInDomain", "Fluid2phils");
    model.component("comp1").physics("ls").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("ls").feature("inl1").selection().set(2, 4, 5);
    model.component("comp1").physics("ls").create("out1", "Outlet", 1);
    model.component("comp1").physics("ls").feature("out1").selection().set(1, 3, 6, 7);
    model.component("comp1").physics("cd").feature("ice1")
         .set("sigmal", new String[]{"sigma*ls.Vf2+sigmae*ls.Vf1", "0", "0", "0", "sigma*ls.Vf2+sigmae*ls.Vf1", "0", "0", "0", "sigma*ls.Vf2+sigmae*ls.Vf1"});

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 3);
    model.component("comp1").mesh("mesh1").feature("size1").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(2, 5);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("cdi").setSolveFor("/frame/material1", false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("str1").set("udist", 0.035);
    model.result("pg1").feature("str1").set("arrowlength", "normalized");
    model.result("pg1").run();
    model.result("pg1").create("surf2", "Surface");
    model.result("pg1").feature("surf2").set("expr", "1");
    model.result("pg1").feature("surf2").set("coloring", "uniform");
    model.result("pg1").feature("surf2").set("color", "gray");
    model.result("pg1").feature("surf2").create("filt1", "Filter");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").feature("filt1").set("expr", "ls.Vf1>=0.5");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{15});
    model.result("pg1").run();
    model.result("pg6").run();
    model.result("pg6").feature("lngr1").setIndex("looplevelinput", "manual", 0);
    model.result("pg6").feature("lngr1").setIndex("looplevel", new int[]{1}, 0);
    model.result("pg6").run();
    model.result("pg6").label("\u5c40\u90e8\u7535\u6d41\u5bc6\u5ea6\u53d8\u5316");
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").run();
    model.result("pg7").label("\u8150\u8680\u4ea7\u7269\u754c\u9762");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").set("looplevel", new int[]{15});
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", "ls.Vf1");
    model.result("pg7").feature("surf1").set("descr", "\u6d41\u4f53 1 \u7684\u4f53\u79ef\u5206\u6570");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").create("con1", "Contour");
    model.result("pg7").feature("con1").set("expr", "ls.Vf1");
    model.result("pg7").feature("con1").set("descr", "\u6d41\u4f53 1 \u7684\u4f53\u79ef\u5206\u6570");
    model.result("pg7").feature("con1").set("levelmethod", "levels");
    model.result("pg7").feature("con1").set("levels", 0.5);
    model.result("pg7").feature("con1").set("coloring", "uniform");
    model.result("pg7").feature("con1").set("color", "black");
    model.result("pg7").run();

    model.title("\u57a2\u4e0b\u8150\u8680");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u6a21\u62df\u9541\u5408\u91d1 (AE44) \u548c\u4f4e\u78b3\u94a2\u4e0e\u76d0\u6c34\u6eb6\u6db2\uff08\u76d0\u6c34\uff09\u63a5\u89e6\u65f6\uff0c\u8150\u8680\u4ea7\u7269\u5bf9\u4e24\u8005\u4e4b\u95f4\u7684\u7535\u5076\u8150\u8680\u7684\u5f71\u54cd\u3002\n\n\u672c\u4f8b\u5206\u522b\u4f7f\u7528\u6c34\u5e73\u96c6\u548c\u53d8\u5f62\u51e0\u4f55\u516c\u5f0f\u6765\u6a21\u62df\u7531\u4e8e\u8150\u8680\u4ea7\u7269\u7684\u6c89\u79ef\u4ee5\u53ca\u9541\u8868\u9762\u7684\u6eb6\u89e3\u4ea7\u751f\u7684\u53d8\u5f62\u8fb9\u754c\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("under_deposit_corrosion.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
