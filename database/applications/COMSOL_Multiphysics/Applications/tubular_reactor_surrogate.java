/*
 * tubular_reactor_surrogate.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:33 by COMSOL 6.3.0.290. */
public class tubular_reactor_surrogate {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Applications");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tds", "DilutedSpecies", new String[][]{{"cA"}});
    model.component("comp1").physics().create("ht", "HeatTransferInFluids", "geom1");
    model.component("comp1").physics().create("cb", "CoefficientFormBoundaryPDE", new String[][]{{"Tj"}});
    model.component("comp1").physics("cb").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics("cb").prop("Units").set("DependentVariableQuantity", "temperature");
    model.component("comp1").physics("cb").prop("Units").set("CustomSourceTermUnit", "W/m");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/tds", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/physics/cb", true);

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new double[]{0.1, 1});
    model.component("comp1").geom("geom1").runPre("fin");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("E", "75362[J/mol]", "Activation energy");
    model.param().set("A", "16.96e12[1/h]", "Frequency factor");
    model.param().set("ke", "0.559[W/m/K]", "Thermal conductivity");
    model.param().set("Diff", "1e-9[m^2/s]", "Diffusion coefficient");
    model.param().set("Uk", "1300[W/m^2/K]", "Overall heat-transfer coefficient");
    model.param().set("dHrx", "-84666[J/mol]", "Heat of reaction");
    model.param().set("T0", "312[K]", "Inlet temperature");
    model.param().set("Ta0", "277[K]", "Inlet temperature of the coolant");
    model.param().set("v0", "v_w0+v_po0+v_m0", "Total flow rate");
    model.param().set("cA0", "n_po0/v0", "Propylene oxide concentration, inlet");
    model.param().set("cB0", "n_w0/v0", "Water concentration, inlet");
    model.param().set("cMe0", "n_m0/v0", "Methanol concentration, inlet");
    model.param().set("Cp0", "(Cp_po*cA0+Cp_m*cMe0+Cp_w*cB0)/rho0", "Heat capacity at inlet");
    model.param().set("rho0", "(cA0*M_po+cB0*M_w+cMe0*M_m)", "Density at inlet");
    model.param().set("Ra", "0.1[m]", "Reactor radius");
    model.param().set("L", "1[m]", "Reactor length");
    model.param().set("M_po", "58.095[g/mol]", "Molar weight, propylene oxide");
    model.param().set("M_m", "32.042[g/mol]", "Molar weight, methanol");
    model.param().set("M_w", "18[g/mol]", "Molar weight, water");
    model.param().set("rho_po_p", "830[kg/m^3]", "Density, propylene oxide");
    model.param().set("rho_m_p", "791.3[kg/m^3]", "Density, methanol");
    model.param().set("rho_w_p", "1000[kg/m^3]", "Density, water");
    model.param().set("Cp_po", "146.54[J/mol/K]", "Specific heat, po");
    model.param().set("Cp_m", "81.095[J/mol/K]", "Specific heat, m");
    model.param().set("Cp_w", "75.36[J/mol/K]", "Specific heat, w");
    model.param().set("Cp_pg", "192.59[J/mol/K]", "Specific heat, pg");
    model.param().set("v_ratio", "3.5", "Ratio water flow rate / (prop+me) flow rate");
    model.param().set("n_po0", "0.1[mol/s]", "Molar flow rate po in");
    model.param().set("n_m0", "v_po0*rho_m_p/M_m", "Molar flow rate m in");
    model.param().set("v_po0", "n_po0*M_po/rho_po_p", "Volumetric flow rate po in");
    model.param().set("v_m0", "v_po0", "Volumetric flow rate m in");
    model.param().set("v_w0", "v_ratio*(v_po0+v_m0)", "Volumetric flow rate w in");
    model.param().set("n_w0", "rho_w_p*v_w0/M_w", "Molar flow rate w in");
    model.param().set("Cpc", "4180[J/(kg*K)]", "Heat capacity, cooling fluid");
    model.param().set("mc", "0.1[kg/s]", "Mass flow rate, cooling fluid");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("u0", "v0/(pi*Ra^2)", "Average flow rate");
    model.component("comp1").variable("var1").set("uz", "2*u0*(1-(r/Ra)^2)", "Laminar flow profile");
    model.component("comp1").variable("var1").set("xA", "(cA0-cA)/cA0", "Conversion species A");
    model.component("comp1").variable("var1").set("cB", "cB0-cA0*xA", "Concentration species B");
    model.component("comp1").variable("var1").set("cC", "cA0*xA", "Concentration species C");
    model.component("comp1").variable("var1").set("rA", "-A*exp(-E/R_const/T)*cA", "Reaction rate");
    model.component("comp1").variable("var1").set("Q", "(-rA)*(-dHrx)", "Heat production term");
    model.component("comp1").variable("var1")
         .set("cpm", "(Cp_po*cA+Cp_m*cMe0+Cp_w*cB)/rho0", "Mixture specific heat");

    model.component("comp1").physics("tds").feature("cdm1").set("u", new String[]{"0", "0", "uz"});
    model.component("comp1").physics("tds").feature("cdm1")
         .set("D_cA", new String[]{"Diff", "0", "0", "0", "Diff", "0", "0", "0", "Diff"});
    model.component("comp1").physics("tds").feature("init1").setIndex("initc", "cA0", 0);
    model.component("comp1").physics("tds").create("reac1", "Reactions", 2);
    model.component("comp1").physics("tds").feature("reac1").selection().set(1);
    model.component("comp1").physics("tds").feature("reac1").setIndex("R_cA", "rA", 0);
    model.component("comp1").physics("tds").create("conc1", "Concentration", 1);
    model.component("comp1").physics("tds").feature("conc1").selection().set(2);
    model.component("comp1").physics("tds").feature("conc1").setIndex("species", true, 0);
    model.component("comp1").physics("tds").feature("conc1").setIndex("c0", "cA0", 0);
    model.component("comp1").physics("tds").create("out1", "Outflow", 1);
    model.component("comp1").physics("tds").feature("out1").selection().set(3);
    model.component("comp1").physics("ht").feature("fluid1").set("u", new String[]{"0", "0", "uz"});
    model.component("comp1").physics("ht").feature("fluid1").set("k_mat", "userdef");
    model.component("comp1").physics("ht").feature("fluid1")
         .set("k", new String[]{"ke", "0", "0", "0", "ke", "0", "0", "0", "ke"});
    model.component("comp1").physics("ht").feature("fluid1").set("fluidType", "gasLiquid");
    model.component("comp1").physics("ht").feature("fluid1").set("rho_mat", "userdef");
    model.component("comp1").physics("ht").feature("fluid1").set("rho", "rho0");
    model.component("comp1").physics("ht").feature("fluid1").set("Cp_mat", "userdef");
    model.component("comp1").physics("ht").feature("fluid1").set("Cp", "cpm");
    model.component("comp1").physics("ht").feature("fluid1").set("gamma_not_IG_mat", "userdef");
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T0");
    model.component("comp1").physics("ht").create("hs1", "HeatSource", 2);
    model.component("comp1").physics("ht").feature("hs1").selection().set(1);
    model.component("comp1").physics("ht").feature("hs1").set("Q0", "Q");
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp1").selection().set(2);
    model.component("comp1").physics("ht").feature("temp1").set("T0", "T0");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf1").selection().set(4);
    model.component("comp1").physics("ht").feature("hf1").set("q0_input", "-Uk*(T-Tj)");
    model.component("comp1").physics("ht").create("ofl1", "ConvectiveOutflow", 1);
    model.component("comp1").physics("ht").feature("ofl1").selection().set(3);
    model.component("comp1").physics("cb").selection().set(4);
    model.component("comp1").physics("cb").feature("cfeq1").setIndex("c", new int[]{0, 0, 0, 0}, 0);
    model.component("comp1").physics("cb").feature("cfeq1").setIndex("f", "2*pi*Ra*Uk*(T-Tj)", 0);
    model.component("comp1").physics("cb").feature("cfeq1").setIndex("be", new int[]{0, 0}, 0);
    model.component("comp1").physics("cb").feature("cfeq1").setIndex("be", new String[]{"0", "Cpc*mc"}, 0);
    model.component("comp1").physics("cb").create("dir1", "DirichletBoundary", 0);
    model.component("comp1").physics("cb").feature("dir1").selection().set(3);
    model.component("comp1").physics("cb").feature("dir1").setIndex("r", "Ta0", 0);

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(2, 3);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemcount", 50);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 0.01);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("reverse", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(1, 4);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemcount", 200);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemratio", 0.01);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("reverse", true);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("stat2", "Stationary");
    model.study("std1").feature("stat").setEntry("activate", "ht", false);

    model.sol().create("sol1");

    model.component("comp1").mesh("mesh1").stat().selection().geom(2);
    model.component("comp1").mesh("mesh1").stat().selection().set(1);
    model.component("comp1").mesh("mesh1").stat().selection().geom(2);
    model.component("comp1").mesh("mesh1").stat().selection().set(1);

    model.sol("sol1").study("std1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "stat");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").set("control", "stat");
    model.sol("sol1").create("s1", "Stationary");
    model.sol("sol1").feature("s1").set("stol", 0.001);
    model.sol("sol1").feature("s1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("s1").create("seDef", "Segregated");
    model.sol("sol1").feature("s1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("s1").feature("fc1").set("dtech", "auto");
    model.sol("sol1").feature("s1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("s1").feature("fc1").set("initstep", 0.01);
    model.sol("sol1").feature("s1").feature("fc1").set("minstep", 1.0E-6);
    model.sol("sol1").feature("s1").create("d1", "Direct");
    model.sol("sol1").feature("s1").feature("d1").label("Direct (Merged)");
    model.sol("sol1").feature("s1").create("i1", "Iterative");
    model.sol("sol1").feature("s1").feature("i1").set("linsolver", "gmres");
    model.sol("sol1").feature("s1").feature("i1").set("prefuntype", "left");
    model.sol("sol1").feature("s1").feature("i1").set("itrestart", 50);
    model.sol("sol1").feature("s1").feature("i1").set("rhob", 400);
    model.sol("sol1").feature("s1").feature("i1").set("maxlinit", 1000);
    model.sol("sol1").feature("s1").feature("i1").set("nlinnormuse", "on");
    model.sol("sol1").feature("s1").feature("i1").label("AMG, concentrations (tds)");
    model.sol("sol1").feature("s1").feature("i1").create("mg1", "Multigrid");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("prefun", "saamg");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("mgcycle", "v");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("maxcoarsedof", 50000);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("strconn", 0.01);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("nullspace", "constant");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("usesmooth", false);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("saamgcompwise", true);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("loweramg", true);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("compactaggregation", false);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").create("sl1", "SORLine");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1").set("iter", 1);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1").set("linerelax", 0.7);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1")
         .set("linealgorithm", "mesh");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1")
         .set("linemethod", "coupled");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1").set("seconditer", 1);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1").set("relax", 0.5);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").create("sl1", "SORLine");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1").set("iter", 1);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1").set("linerelax", 0.7);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1")
         .set("linealgorithm", "mesh");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1")
         .set("linemethod", "coupled");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1").set("seconditer", 1);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1").set("relax", 0.5);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s1").feature("fc1").set("linsolver", "d1");
    model.sol("sol1").feature("s1").feature("fc1").set("dtech", "auto");
    model.sol("sol1").feature("s1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("s1").feature("fc1").set("initstep", 0.01);
    model.sol("sol1").feature("s1").feature("fc1").set("minstep", 1.0E-6);
    model.sol("sol1").feature("s1").feature().remove("fcDef");
    model.sol("sol1").feature("s1").feature().remove("seDef");
    model.sol("sol1").create("su1", "StoreSolution");
    model.sol("sol1").create("st2", "StudyStep");
    model.sol("sol1").feature("st2").set("study", "std1");
    model.sol("sol1").feature("st2").set("studystep", "stat2");
    model.sol("sol1").create("v2", "Variables");
    model.sol("sol1").feature("v2").set("initmethod", "sol");
    model.sol("sol1").feature("v2").set("initsol", "sol1");
    model.sol("sol1").feature("v2").set("initsoluse", "sol2");
    model.sol("sol1").feature("v2").set("notsolmethod", "sol");
    model.sol("sol1").feature("v2").set("notsol", "sol1");
    model.sol("sol1").feature("v2").set("control", "stat2");
    model.sol("sol1").create("s2", "Stationary");
    model.sol("sol1").feature("s2").set("stol", 0.001);
    model.sol("sol1").feature("s2").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("s2").create("seDef", "Segregated");
    model.sol("sol1").feature("s2").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("s2").feature("fc1").set("dtech", "auto");
    model.sol("sol1").feature("s2").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("s2").feature("fc1").set("initstep", 0.01);
    model.sol("sol1").feature("s2").feature("fc1").set("minstep", 1.0E-6);
    model.sol("sol1").feature("s2").create("d1", "Direct");
    model.sol("sol1").feature("s2").feature("d1").set("linsolver", "mumps");
    model.sol("sol1").feature("s2").feature("d1").set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s2").feature("d1").label("Direct (Merged)");
    model.sol("sol1").feature("s2").create("i1", "Iterative");
    model.sol("sol1").feature("s2").feature("i1").set("linsolver", "gmres");
    model.sol("sol1").feature("s2").feature("i1").set("prefuntype", "left");
    model.sol("sol1").feature("s2").feature("i1").set("itrestart", 50);
    model.sol("sol1").feature("s2").feature("i1").set("rhob", 400);
    model.sol("sol1").feature("s2").feature("i1").set("maxlinit", 1000);
    model.sol("sol1").feature("s2").feature("i1").set("nlinnormuse", "on");
    model.sol("sol1").feature("s2").feature("i1").label("AMG, concentrations (tds)");
    model.sol("sol1").feature("s2").feature("i1").create("mg1", "Multigrid");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").set("prefun", "saamg");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").set("mgcycle", "v");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").set("maxcoarsedof", 50000);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").set("strconn", 0.01);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").set("nullspace", "constant");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").set("usesmooth", false);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").set("saamgcompwise", true);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").set("loweramg", true);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").set("compactaggregation", false);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").create("sl1", "SORLine");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sl1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sl1").set("iter", 1);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sl1").set("linerelax", 0.7);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sl1")
         .set("linealgorithm", "mesh");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sl1")
         .set("linemethod", "coupled");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sl1").set("seconditer", 1);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sl1").set("relax", 0.5);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").create("sl1", "SORLine");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").feature("sl1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").feature("sl1").set("iter", 1);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").feature("sl1").set("linerelax", 0.7);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").feature("sl1")
         .set("linealgorithm", "mesh");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").feature("sl1")
         .set("linemethod", "coupled");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").feature("sl1").set("seconditer", 1);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").feature("sl1").set("relax", 0.5);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s2").create("i2", "Iterative");
    model.sol("sol1").feature("s2").feature("i2").set("linsolver", "gmres");
    model.sol("sol1").feature("s2").feature("i2").set("prefuntype", "left");
    model.sol("sol1").feature("s2").feature("i2").set("itrestart", 50);
    model.sol("sol1").feature("s2").feature("i2").set("rhob", 20);
    model.sol("sol1").feature("s2").feature("i2").set("maxlinit", 10000);
    model.sol("sol1").feature("s2").feature("i2").set("nlinnormuse", "on");
    model.sol("sol1").feature("s2").feature("i2").label("AMG, heat transfer variables (ht)");
    model.sol("sol1").feature("s2").feature("i2").create("mg1", "Multigrid");
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").set("prefun", "saamg");
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").set("mgcycle", "v");
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").set("maxcoarsedof", 50000);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").set("strconn", 0.01);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").set("nullspace", "constant");
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").set("usesmooth", false);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").set("saamgcompwise", true);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").set("loweramg", true);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").set("compactaggregation", false);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("pr").create("so1", "SOR");
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("pr").feature("so1").set("iter", 2);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("pr").feature("so1").set("relax", 0.9);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("po").create("so1", "SOR");
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("po").feature("so1").set("iter", 2);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("po").feature("so1").set("relax", 0.9);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s2").feature("fc1").set("linsolver", "d1");
    model.sol("sol1").feature("s2").feature("fc1").set("dtech", "auto");
    model.sol("sol1").feature("s2").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("s2").feature("fc1").set("initstep", 0.01);
    model.sol("sol1").feature("s2").feature("fc1").set("minstep", 1.0E-6);
    model.sol("sol1").feature("s2").feature().remove("fcDef");
    model.sol("sol1").feature("s2").feature().remove("seDef");
    model.sol("sol1").feature("v2").set("notsolnum", "auto");
    model.sol("sol1").feature("v2").set("notsolvertype", "solnum");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").label("Concentration (tds)");
    model.result("pg1").set("titletype", "custom");
    model.result("pg1").set("prefixintitle", "");
    model.result("pg1").set("expressionintitle", false);
    model.result("pg1").set("typeintitle", true);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"cA"});
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("expr", new String[]{"tds.tflux_cAr", "tds.tflux_cAz"});
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("recover", "pprint");
    model.result("pg1").feature("str1").set("pointtype", "arrow");
    model.result("pg1").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg1").feature("str1").set("color", "gray");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "dset1");
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("hasspacevars", false);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "rev1");
    model.result("pg2").label("Concentration, 3D (tds)");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"cA"});
    model.result("pg2").set("titletype", "custom");
    model.result("pg2").set("typeintitle", false);
    model.result("pg2").set("prefixintitle", "");
    model.result("pg2").set("expressionintitle", false);
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("Temperature (ht)");
    model.result("pg3").set("dataisaxisym", "off");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").set("defaultPlotID", "ht/HT_PhysicsInterfaces/icom8/pdef1/pcond2/pcond4/pg2");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("solutionparams", "parent");
    model.result("pg3").feature("surf1").set("expr", "T");
    model.result("pg3").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").create("line1", "Line");
    model.result("pg4").label("Coefficient Form Boundary PDE");
    model.result("pg4").feature("line1").set("expr", "Tj");
    model.result("pg1").run();
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").setIndex("genpoints", "Ra", 1, 0);
    model.result().dataset("cln1").set("genparaactive", true);
    model.result().dataset("cln1").set("genparadist", "0.5*L 1*L");
    model.result().dataset().create("mir1", "Mirror2D");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").run();
    model.result("pg5").label("Temperature (Mirrored)");
    model.result("pg5").set("data", "mir1");
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "Temperature Surface");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "Radial location (m)");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "Axial location (m)");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "T");
    model.result("pg5").feature("surf1").set("descr", "Temperature");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("Conversion");
    model.result("pg6").set("title", "Conversion Surface");
    model.result("pg6").run();
    model.result("pg6").feature("surf1").set("expr", "xA");
    model.result("pg6").feature("surf1").set("descr", "Conversion species A");
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("Temperature, 1D");
    model.result("pg7").set("data", "cln1");
    model.result("pg7").set("titletype", "manual");
    model.result("pg7").set("title", "Radial Temperature Profiles");
    model.result("pg7").set("xlabelactive", true);
    model.result("pg7").set("xlabel", "Radial location (m)");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "Temperature (K)");
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg7").feature("lngr1").set("linewidth", "preference");
    model.result("pg7").feature("lngr1").set("expr", "T");
    model.result("pg7").feature("lngr1").set("descr", "Temperature");
    model.result("pg7").feature("lngr1").set("linestyle", "cycle");
    model.result("pg7").feature("lngr1").set("linecolor", "black");
    model.result("pg7").feature("lngr1").set("legend", true);
    model.result("pg7").feature("lngr1").set("legendmethod", "manual");
    model.result("pg7").feature("lngr1").setIndex("legends", "Inlet", 0);
    model.result("pg7").feature("lngr1").setIndex("legends", "Half axial location", 1);
    model.result("pg7").feature("lngr1").setIndex("legends", "Outlet", 2);
    model.result("pg7").run();
    model.result("pg7").set("legendpos", "lowerleft");
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").label("Conversion, 1D");
    model.result("pg8").set("ylabel", "Conversion");
    model.result("pg8").run();
    model.result("pg8").feature("lngr1").set("expr", "xA");
    model.result("pg8").feature("lngr1").set("descr", "Conversion species A");
    model.result("pg8").run();
    model.result("pg8").set("legendpos", "upperleft");
    model.result("pg8").run();
    model.result("pg4").run();
    model.result("pg4").label("Temperature Cooling Jacket");
    model.result("pg4").run();
    model.result("pg4").feature("line1").set("linetype", "tube");
    model.result("pg4").feature("line1").set("radiusexpr", "3");
    model.result("pg4").run();
    model.result("pg2").run();
    model.result("pg2").run();

    model.title("\u5e26\u975e\u7b49\u6e29\u51b7\u5374\u5939\u5957\u7684\u7ba1\u5f0f\u53cd\u5e94\u5668");

    model
         .description("\u672c\u4f8b\u63cf\u8ff0\u7ba1\u5f0f\u53cd\u5e94\u5668\u4e2d\u73af\u6c27\u4e19\u70f7 (A) \u4e0e\u6c34 (B) \u53d1\u751f\u53cd\u5e94\u4ea7\u751f\u4e19\u4e8c\u9187 (C)\uff1a\n\nA + B -> C\n\n\u8fd9\u91cc\u6c34\u662f\u6eb6\u5242\uff0c\u53cd\u5e94\u52a8\u529b\u5b66\u53ef\u4ee5\u63cf\u8ff0\u4e3a\u73af\u6c27\u4e19\u70f7\u7684\u4e00\u7ea7\u53cd\u5e94\n\nR = k*C_A\n\n\u53e6\u5916\uff0c\u6839\u636e\n\nR = kf*C_A*C_B - kr*C_C\n\n\u8fd8\u4f1a\u5b9e\u73b0\u4e00\u4e2a\u4e8c\u7ea7\u53cd\u5e94\uff0c\u6b64\u53cd\u5e94\u4e3a\u653e\u70ed\u53cd\u5e94\uff0c\u56e0\u6b64\u4f7f\u7528\u51b7\u5374\u5939\u5957\u4f7f\u53cd\u5e94\u5668\u964d\u6e29\u3002\u53cd\u5e94\u5668\u5728\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u6a21\u5f0f\u4e0b\u5efa\u6a21\uff0c\u6839\u636e\u4eff\u771f\u7ed3\u679c\u53ef\u77e5\uff0c\u6210\u5206\u548c\u6e29\u5ea6\u5728\u5f84\u5411\u548c\u8f74\u5411\u90fd\u53d1\u751f\u4e86\u53d8\u5316\u3002");

    return model;
  }

  public static Model run2(Model model) {

    model.label("tubular_reactor.mph");

    model.result("pg2").run();

    model.param().set("r0", "0[m]");
    model.param().descr("r0", "Radial position");
    model.param().set("z0", "0[m]");
    model.param().descr("z0", "Axial position");

    model.component("comp1").probe().create("pdom1", "DomainPoint");
    model.component("comp1").probe("pdom1").setIndex("coords2", "r0", 0);
    model.component("comp1").probe("pdom1").setIndex("coords2", "z0", 1);
    model.component("comp1").probe("pdom1").feature("ppb1").set("expr", "T");
    model.component("comp1").probe("pdom1").feature().create("ppb2", "PointExpr");
    model.component("comp1").probe("pdom1").feature("ppb2").set("expr", "xA");

    model.study("std1").create("sm", "SurrogateModelTraining");
    model.study("std1").feature("sm").setIndex("qoiexpression", "", 0);
    model.study("std1").feature("sm").setIndex("descr", "", 0);
    model.study("std1").feature("sm").setIndex("qoisolutionindv", "parent", 0);
    model.study("std1").feature("sm").setIndex("qoiexpression", "", 0);
    model.study("std1").feature("sm").setIndex("descr", "", 0);
    model.study("std1").feature("sm").setIndex("qoisolutionindv", "parent", 0);
    model.study("std1").feature("sm").setIndex("qoiexpression", "", 1);
    model.study("std1").feature("sm").setIndex("descr", "", 1);
    model.study("std1").feature("sm").setIndex("qoisolutionindv", "parent", 1);
    model.study("std1").feature("sm").setIndex("qoiexpression", "", 1);
    model.study("std1").feature("sm").setIndex("descr", "", 1);
    model.study("std1").feature("sm").setIndex("qoisolutionindv", "parent", 1);
    model.study("std1").feature("sm").setIndex("qoiexpression", "comp1.ppb1", 0);
    model.study("std1").feature("sm").setIndex("descr", "Temperature", 0);
    model.study("std1").feature("sm").setIndex("qoiexpression", "comp1.ppb2", 1);
    model.study("std1").feature("sm").setIndex("descr", "Conversion", 1);
    model.study("std1").feature("sm").setIndex("pname", "E", 0);
    model.study("std1").feature("sm").setEntry("sourceType", "col1", "analytic");
    model.study("std1").feature("sm").setIndex("paramDescription", "J/mol", 0);
    model.study("std1").feature("sm").setIndex("pname", "E", 0);
    model.study("std1").feature("sm").setEntry("sourceType", "col1", "analytic");
    model.study("std1").feature("sm").setIndex("paramDescription", "J/mol", 0);
    model.study("std1").feature("sm").setIndex("pname", "A", 1);
    model.study("std1").feature("sm").setEntry("sourceType", "col2", "analytic");
    model.study("std1").feature("sm").setIndex("paramDescription", "1/s", 1);
    model.study("std1").feature("sm").setIndex("pname", "A", 1);
    model.study("std1").feature("sm").setEntry("sourceType", "col2", "analytic");
    model.study("std1").feature("sm").setIndex("paramDescription", "1/s", 1);
    model.study("std1").feature("sm").setIndex("pname", "ke", 2);
    model.study("std1").feature("sm").setEntry("sourceType", "col3", "analytic");
    model.study("std1").feature("sm").setIndex("paramDescription", "W/(m*K)", 2);
    model.study("std1").feature("sm").setIndex("pname", "ke", 2);
    model.study("std1").feature("sm").setEntry("sourceType", "col3", "analytic");
    model.study("std1").feature("sm").setIndex("paramDescription", "W/(m*K)", 2);
    model.study("std1").feature("sm").setIndex("pname", "Diff", 3);
    model.study("std1").feature("sm").setEntry("sourceType", "col4", "analytic");
    model.study("std1").feature("sm").setIndex("paramDescription", "m^2/s", 3);
    model.study("std1").feature("sm").setIndex("pname", "Diff", 3);
    model.study("std1").feature("sm").setEntry("sourceType", "col4", "analytic");
    model.study("std1").feature("sm").setIndex("paramDescription", "m^2/s", 3);
    model.study("std1").feature("sm").setIndex("pname", "Uk", 4);
    model.study("std1").feature("sm").setEntry("sourceType", "col5", "analytic");
    model.study("std1").feature("sm").setIndex("paramDescription", "W/(m^2*K)", 4);
    model.study("std1").feature("sm").setIndex("pname", "Uk", 4);
    model.study("std1").feature("sm").setEntry("sourceType", "col5", "analytic");
    model.study("std1").feature("sm").setIndex("paramDescription", "W/(m^2*K)", 4);
    model.study("std1").feature("sm").setIndex("pname", "r0", 0);
    model.study("std1").feature("sm").setIndex("pname", "z0", 1);
    model.study("std1").feature("sm").setIndex("pname", "E", 2);
    model.study("std1").feature("sm").setIndex("pname", "ke", 3);
    model.study("std1").feature("sm").setIndex("pname", "dHrx", 4);
    model.study("std1").feature("sm").setEntry("lboundselection", "col1", "0");
    model.study("std1").feature("sm").setEntry("uboundselection", "col1", "0.1");
    model.study("std1").feature("sm").setEntry("lboundselection", "col2", "0");
    model.study("std1").feature("sm").setEntry("uboundselection", "col2", "1");
    model.study("std1").feature("sm").setEntry("lboundselection", "col3", "71518");
    model.study("std1").feature("sm").setEntry("uboundselection", "col3", "79205");
    model.study("std1").feature("sm").setEntry("lboundselection", "col4", "0.0559");
    model.study("std1").feature("sm").setEntry("uboundselection", "col4", "5.6");
    model.study("std1").feature("sm").setEntry("lboundselection", "col5", "-101600");
    model.study("std1").feature("sm").setEntry("uboundselection", "col5", "-67733");
    model.study("std1").feature("sm").set("nsolvenonadp", 4000);
    model.study("std1").feature("sm").set("errorhandling", "later");

    model.component("comp1").mesh("mesh1").stat().selection().geom(2);
    model.component("comp1").mesh("mesh1").stat().selection().set(1);
    model.component("comp1").mesh("mesh1").stat().selection().geom(2);
    model.component("comp1").mesh("mesh1").stat().selection().set(1);

    model.sol("sol1").study("std1");

    model.study("std1").feature("stat2").set("notsolnum", "auto");
    model.study("std1").feature("stat2").set("notsolvertype", "solnum");
    model.study("std1").feature("stat2").set("notsolnumhide", "off");
    model.study("std1").feature("stat2").set("notstudyhide", "off");
    model.study("std1").feature("stat2").set("notsolhide", "off");

    model.sol("sol2").copySolution("sol3");

    model.study("std1").feature("stat2").set("notlistsolnum", 1);
    model.study("std1").feature("stat2").set("notsolnum", "auto");
    model.study("std1").feature("stat2").set("listsolnum", 1);
    model.study("std1").feature("stat2").set("solnum", "auto");

    model.result().dataset("dset2").set("solution", "none");

    model.sol("sol1").feature().remove("s2");
    model.sol("sol1").feature().remove("v2");
    model.sol("sol1").feature().remove("st2");
    model.sol("sol1").feature().remove("su1");
    model.sol("sol1").feature().remove("s1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol3").copySolution("sol2");
    model.sol().remove("sol3");
    model.sol("sol2").label("Solution Store 1");

    model.result().dataset().remove("dset4");

    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "stat");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").set("control", "stat");
    model.sol("sol1").create("s1", "Stationary");
    model.sol("sol1").feature("s1").set("stol", 0.001);
    model.sol("sol1").feature("s1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("s1").create("seDef", "Segregated");
    model.sol("sol1").feature("s1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("s1").feature("fc1").set("dtech", "auto");
    model.sol("sol1").feature("s1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("s1").feature("fc1").set("initstep", 0.01);
    model.sol("sol1").feature("s1").feature("fc1").set("minstep", 1.0E-6);
    model.sol("sol1").feature("s1").create("d1", "Direct");
    model.sol("sol1").feature("s1").feature("d1").label("Direct (\u5df2\u5408\u5e76)");
    model.sol("sol1").feature("s1").create("i1", "Iterative");
    model.sol("sol1").feature("s1").feature("i1").set("linsolver", "gmres");
    model.sol("sol1").feature("s1").feature("i1").set("prefuntype", "left");
    model.sol("sol1").feature("s1").feature("i1").set("itrestart", 50);
    model.sol("sol1").feature("s1").feature("i1").set("rhob", 400);
    model.sol("sol1").feature("s1").feature("i1").set("maxlinit", 1000);
    model.sol("sol1").feature("s1").feature("i1").set("nlinnormuse", "on");
    model.sol("sol1").feature("s1").feature("i1").label("AMG\uff0c\u6d53\u5ea6 (tds)");
    model.sol("sol1").feature("s1").feature("i1").create("mg1", "Multigrid");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("prefun", "saamg");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("mgcycle", "v");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("maxcoarsedof", 50000);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("strconn", 0.01);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("nullspace", "constant");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("usesmooth", false);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("saamgcompwise", true);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("loweramg", true);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("compactaggregation", false);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").create("sl1", "SORLine");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1").set("iter", 1);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1").set("linerelax", 0.7);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1")
         .set("linealgorithm", "mesh");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1")
         .set("linemethod", "coupled");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1").set("seconditer", 1);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1").set("relax", 0.5);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").create("sl1", "SORLine");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1").set("iter", 1);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1").set("linerelax", 0.7);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1")
         .set("linealgorithm", "mesh");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1")
         .set("linemethod", "coupled");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1").set("seconditer", 1);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1").set("relax", 0.5);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s1").feature("fc1").set("linsolver", "d1");
    model.sol("sol1").feature("s1").feature("fc1").set("dtech", "auto");
    model.sol("sol1").feature("s1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("s1").feature("fc1").set("initstep", 0.01);
    model.sol("sol1").feature("s1").feature("fc1").set("minstep", 1.0E-6);
    model.sol("sol1").feature("s1").feature().remove("fcDef");
    model.sol("sol1").feature("s1").feature().remove("seDef");
    model.sol("sol1").create("su1", "StoreSolution");
    model.sol("sol1").feature("su1").set("sol", "sol2");
    model.sol("sol1").feature("su1").label("Solution Store 1");
    model.sol("sol1").create("st2", "StudyStep");
    model.sol("sol1").feature("st2").set("study", "std1");
    model.sol("sol1").feature("st2").set("studystep", "stat2");

    model.study("std1").feature("stat2").set("initsoluse", "sol2");

    model.sol("sol1").create("v2", "Variables");
    model.sol("sol1").feature("v2").set("initmethod", "sol");
    model.sol("sol1").feature("v2").set("initsol", "sol1");
    model.sol("sol1").feature("v2").set("initsoluse", "sol2");
    model.sol("sol1").feature("v2").set("notsolmethod", "sol");
    model.sol("sol1").feature("v2").set("notsol", "sol1");
    model.sol("sol1").feature("v2").set("control", "stat2");
    model.sol("sol1").create("s2", "Stationary");
    model.sol("sol1").feature("s2").set("stol", 0.001);
    model.sol("sol1").feature("s2").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("s2").create("seDef", "Segregated");
    model.sol("sol1").feature("s2").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("s2").feature("fc1").set("dtech", "auto");
    model.sol("sol1").feature("s2").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("s2").feature("fc1").set("initstep", 0.01);
    model.sol("sol1").feature("s2").feature("fc1").set("minstep", 1.0E-6);
    model.sol("sol1").feature("s2").create("d1", "Direct");
    model.sol("sol1").feature("s2").feature("d1").set("linsolver", "mumps");
    model.sol("sol1").feature("s2").feature("d1").set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s2").feature("d1").label("Direct (\u5df2\u5408\u5e76)");
    model.sol("sol1").feature("s2").create("i1", "Iterative");
    model.sol("sol1").feature("s2").feature("i1").set("linsolver", "gmres");
    model.sol("sol1").feature("s2").feature("i1").set("prefuntype", "left");
    model.sol("sol1").feature("s2").feature("i1").set("itrestart", 50);
    model.sol("sol1").feature("s2").feature("i1").set("rhob", 400);
    model.sol("sol1").feature("s2").feature("i1").set("maxlinit", 1000);
    model.sol("sol1").feature("s2").feature("i1").set("nlinnormuse", "on");
    model.sol("sol1").feature("s2").feature("i1").label("AMG\uff0c\u6d53\u5ea6 (tds)");
    model.sol("sol1").feature("s2").feature("i1").create("mg1", "Multigrid");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").set("prefun", "saamg");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").set("mgcycle", "v");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").set("maxcoarsedof", 50000);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").set("strconn", 0.01);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").set("nullspace", "constant");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").set("usesmooth", false);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").set("saamgcompwise", true);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").set("loweramg", true);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").set("compactaggregation", false);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").create("sl1", "SORLine");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sl1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sl1").set("iter", 1);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sl1").set("linerelax", 0.7);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sl1")
         .set("linealgorithm", "mesh");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sl1")
         .set("linemethod", "coupled");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sl1").set("seconditer", 1);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sl1").set("relax", 0.5);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").create("sl1", "SORLine");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").feature("sl1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").feature("sl1").set("iter", 1);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").feature("sl1").set("linerelax", 0.7);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").feature("sl1")
         .set("linealgorithm", "mesh");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").feature("sl1")
         .set("linemethod", "coupled");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").feature("sl1").set("seconditer", 1);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").feature("sl1").set("relax", 0.5);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s2").create("i2", "Iterative");
    model.sol("sol1").feature("s2").feature("i2").set("linsolver", "gmres");
    model.sol("sol1").feature("s2").feature("i2").set("prefuntype", "left");
    model.sol("sol1").feature("s2").feature("i2").set("itrestart", 50);
    model.sol("sol1").feature("s2").feature("i2").set("rhob", 20);
    model.sol("sol1").feature("s2").feature("i2").set("maxlinit", 10000);
    model.sol("sol1").feature("s2").feature("i2").set("nlinnormuse", "on");
    model.sol("sol1").feature("s2").feature("i2").label("AMG\uff0c\u4f20\u70ed\u53d8\u91cf (ht)");
    model.sol("sol1").feature("s2").feature("i2").create("mg1", "Multigrid");
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").set("prefun", "saamg");
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").set("mgcycle", "v");
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").set("maxcoarsedof", 50000);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").set("strconn", 0.01);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").set("nullspace", "constant");
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").set("usesmooth", false);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").set("saamgcompwise", true);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").set("loweramg", true);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").set("compactaggregation", false);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("pr").create("so1", "SOR");
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("pr").feature("so1").set("iter", 2);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("pr").feature("so1").set("relax", 0.9);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("po").create("so1", "SOR");
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("po").feature("so1").set("iter", 2);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("po").feature("so1").set("relax", 0.9);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s2").feature("fc1").set("linsolver", "d1");
    model.sol("sol1").feature("s2").feature("fc1").set("dtech", "auto");
    model.sol("sol1").feature("s2").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("s2").feature("fc1").set("initstep", 0.01);
    model.sol("sol1").feature("s2").feature("fc1").set("minstep", 1.0E-6);
    model.sol("sol1").feature("s2").feature().remove("fcDef");
    model.sol("sol1").feature("s2").feature().remove("seDef");

    model.result().dataset("dset2").set("solution", "sol2");

    model.sol("sol1").feature("v2").set("notsolnum", "auto");
    model.sol("sol1").feature("v2").set("notsolvertype", "solnum");

    model.study("std1").feature("stat2").set("notsolnum", "auto");
    model.study("std1").feature("stat2").set("notsolvertype", "solnum");
    model.study("std1").feature("stat2").set("notsolnumhide", "off");
    model.study("std1").feature("stat2").set("notstudyhide", "off");
    model.study("std1").feature("stat2").set("notsolhide", "off");

    model.sol("sol1").attach("std1");

    model.study("std1").feature("sm").set("computeaction", "recompute");

    model.batch().create("pd1", "DesignofExperiments");
    model.batch("pd1").study("std1");
    model.batch("pd1").set("lhssettings", "auto");
    model.batch("pd1").create("so1", "Solutionseq");
    model.batch("pd1").feature("so1").set("seq", "sol1");
    model.batch("pd1").feature("so1").set("store", "on");
    model.batch("pd1").feature("so1").set("clear", "on");
    model.batch("pd1").feature("so1").set("psol", "none");
    model.batch("pd1").attach("std1");
    model.batch("pd1").set("control", "sm");

    model.study("std1").feature("sm").set("computeaction", "recompute");

    model.sol().create("sol3");
    model.sol("sol3").study("std1");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("pd1").feature("so1").set("psol", "sol3");

    model.component("comp1").probe("pdom1").genResult("none");

    model.batch("pd1").run("compute");

    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").set("data", "dset3");
    model.result("pg9").setIndex("looplevel", 1, 0);
    model.result("pg9").label("\u6d53\u5ea6 (tds)");
    model.result("pg9").set("titletype", "custom");
    model.result("pg9").set("prefixintitle", "");
    model.result("pg9").set("expressionintitle", false);
    model.result("pg9").set("typeintitle", true);
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", new String[]{"cA"});
    model.result("pg9").create("str1", "Streamline");
    model.result("pg9").feature("str1").set("expr", new String[]{"tds.tflux_cAr", "tds.tflux_cAz"});
    model.result("pg9").feature("str1").set("posmethod", "uniform");
    model.result("pg9").feature("str1").set("recover", "pprint");
    model.result("pg9").feature("str1").set("pointtype", "arrow");
    model.result("pg9").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg9").feature("str1").set("color", "gray");
    model.result().dataset().create("rev2", "Revolve2D");
    model.result().dataset("rev2").set("data", "dset3");
    model.result().dataset("rev2").set("revangle", 225);
    model.result().dataset("rev2").set("startangle", -90);
    model.result().dataset("rev2").set("hasspacevars", false);
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").set("data", "rev2");
    model.result("pg10").setIndex("looplevel", 1, 0);
    model.result("pg10").label("\u6d53\u5ea6, 3D (tds)");
    model.result("pg10").create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("expr", new String[]{"cA"});
    model.result("pg10").set("titletype", "custom");
    model.result("pg10").set("typeintitle", false);
    model.result("pg10").set("prefixintitle", "");
    model.result("pg10").set("expressionintitle", false);
    model.result().create("pg11", "PlotGroup2D");
    model.result("pg11").label("\u6e29\u5ea6 (ht)");
    model.result("pg11").set("data", "dset3");
    model.result("pg11").setIndex("looplevel", 1, 0);
    model.result("pg11").set("dataisaxisym", "off");
    model.result("pg11").set("data", "dset3");
    model.result("pg11").setIndex("looplevel", 1, 0);
    model.result("pg11").set("defaultPlotID", "ht/HT_PhysicsInterfaces/icom8/pdef1/pcond2/pcond4/pg2");
    model.result("pg11").feature().create("surf1", "Surface");
    model.result("pg11").feature("surf1").set("showsolutionparams", "on");
    model.result("pg11").feature("surf1").set("solutionparams", "parent");
    model.result("pg11").feature("surf1").set("expr", "T");
    model.result("pg11").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg11").feature("surf1").set("showsolutionparams", "on");
    model.result("pg11").feature("surf1").set("data", "parent");
    model.result().create("pg12", "PlotGroup2D");
    model.result("pg12").set("data", "dset3");
    model.result("pg12").setIndex("looplevel", 1, 0);
    model.result("pg12").create("line1", "Line");
    model.result("pg12").label("Coefficient Form Boundary PDE");
    model.result("pg12").feature("line1").set("expr", "Tj");
    model.result().dataset().create("grid1", "Grid2D");
    model.result().dataset("grid1").set("source", "data");
    model.result().dataset("grid1").set("par1", "x1");
    model.result().dataset("grid1").set("parmax1", 0.1);
    model.result().dataset("grid1").set("par2", "x2");
    model.result().dataset("grid1").set("res1", 25);
    model.result().dataset("grid1").set("res2", 75);
    model.result().dataset("grid1").set("source", "function");

    model.func().create("dnn1", "DNN");
    model.func("dnn1").setIndex("layertype", "dense", 0);
    model.func("dnn1").setIndex("outfeatures", 1, 0);
    model.func("dnn1").setIndex("activation", "tanh", 0);
    model.func("dnn1").setIndex("layerDescription", "", 0);
    model.func("dnn1").setIndex("layertype", "dense", 0);
    model.func("dnn1").setIndex("outfeatures", 1, 0);
    model.func("dnn1").setIndex("activation", "tanh", 0);
    model.func("dnn1").setIndex("layerDescription", "", 0);
    model.func("dnn1").setIndex("layertype", "dense", 1);
    model.func("dnn1").setIndex("outfeatures", 1, 1);
    model.func("dnn1").setIndex("activation", "tanh", 1);
    model.func("dnn1").setIndex("layerDescription", "", 1);
    model.func("dnn1").setIndex("layertype", "dense", 1);
    model.func("dnn1").setIndex("outfeatures", 1, 1);
    model.func("dnn1").setIndex("activation", "tanh", 1);
    model.func("dnn1").setIndex("layerDescription", "", 1);
    model.func("dnn1").setIndex("layertype", "dense", 2);
    model.func("dnn1").setIndex("outfeatures", 1, 2);
    model.func("dnn1").setIndex("activation", "tanh", 2);
    model.func("dnn1").setIndex("layerDescription", "", 2);
    model.func("dnn1").setIndex("layertype", "dense", 2);
    model.func("dnn1").setIndex("outfeatures", 1, 2);
    model.func("dnn1").setIndex("activation", "tanh", 2);
    model.func("dnn1").setIndex("layerDescription", "", 2);
    model.func("dnn1").setIndex("layertype", "dense", 3);
    model.func("dnn1").setIndex("outfeatures", 1, 3);
    model.func("dnn1").setIndex("activation", "tanh", 3);
    model.func("dnn1").setIndex("layerDescription", "", 3);
    model.func("dnn1").setIndex("layertype", "dense", 3);
    model.func("dnn1").setIndex("outfeatures", 1, 3);
    model.func("dnn1").setIndex("activation", "tanh", 3);
    model.func("dnn1").setIndex("layerDescription", "", 3);
    model.func("dnn1").setIndex("layertype", "dense", 4);
    model.func("dnn1").setIndex("outfeatures", 1, 4);
    model.func("dnn1").setIndex("activation", "tanh", 4);
    model.func("dnn1").setIndex("layerDescription", "", 4);
    model.func("dnn1").setIndex("layertype", "dense", 4);
    model.func("dnn1").setIndex("outfeatures", 1, 4);
    model.func("dnn1").setIndex("activation", "tanh", 4);
    model.func("dnn1").setIndex("layerDescription", "", 4);
    model.func("dnn1").setIndex("outfeatures", "50", 0);
    model.func("dnn1").setIndex("outfeatures", "40", 1);
    model.func("dnn1").setIndex("outfeatures", "30", 2);
    model.func("dnn1").setIndex("outfeatures", "20", 3);
    model.func("dnn1").setIndex("outfeatures", "2", 4);
    model.func("dnn1").set("source", "resultTable");
    model.func("dnn1").setEntry("columnType", "col6", "value");
    model.func("dnn1").setEntry("funcs", "col6", "dnn1_1");
    model.func("dnn1").setEntry("funcs", "col7", "dnn1_2");
    model.func("dnn1").set("epochs", 50000);
    model.func("dnn1").run();

    model.study("std1").feature("sm").active(false);

    model.component("comp1").mesh("mesh1").stat().selection().geom(2);
    model.component("comp1").mesh("mesh1").stat().selection().set(1);
    model.component("comp1").mesh("mesh1").stat().selection().geom(2);
    model.component("comp1").mesh("mesh1").stat().selection().set(1);

    model.sol("sol1").study("std1");

    model.study("std1").feature("stat2").set("notsolnum", "auto");
    model.study("std1").feature("stat2").set("notsolvertype", "solnum");
    model.study("std1").feature("stat2").set("notsolnumhide", "off");
    model.study("std1").feature("stat2").set("notstudyhide", "off");
    model.study("std1").feature("stat2").set("notsolhide", "off");

    model.sol("sol2").copySolution("sol5");

    model.study("std1").feature("stat2").set("notlistsolnum", 1);
    model.study("std1").feature("stat2").set("notsolnum", "auto");
    model.study("std1").feature("stat2").set("listsolnum", 1);
    model.study("std1").feature("stat2").set("solnum", "auto");

    model.result().dataset("dset2").set("solution", "none");

    model.sol("sol1").feature().remove("s2");
    model.sol("sol1").feature().remove("v2");
    model.sol("sol1").feature().remove("st2");
    model.sol("sol1").feature().remove("su1");
    model.sol("sol1").feature().remove("s1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol5").copySolution("sol2");
    model.sol().remove("sol5");
    model.sol("sol2").label("\u89e3\u5b58\u50a8 1");

    model.result().dataset().remove("dset6");

    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "stat");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").set("control", "stat");
    model.sol("sol1").create("s1", "Stationary");
    model.sol("sol1").feature("s1").set("stol", 0.001);
    model.sol("sol1").feature("s1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("s1").create("seDef", "Segregated");
    model.sol("sol1").feature("s1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("s1").feature("fc1").set("dtech", "auto");
    model.sol("sol1").feature("s1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("s1").feature("fc1").set("initstep", 0.01);
    model.sol("sol1").feature("s1").feature("fc1").set("minstep", 1.0E-6);
    model.sol("sol1").feature("s1").create("d1", "Direct");
    model.sol("sol1").feature("s1").feature("d1").label("Direct (\u5df2\u5408\u5e76)");
    model.sol("sol1").feature("s1").create("i1", "Iterative");
    model.sol("sol1").feature("s1").feature("i1").set("linsolver", "gmres");

    return model;
  }

  public static Model run3(Model model) {
    model.sol("sol1").feature("s1").feature("i1").set("prefuntype", "left");
    model.sol("sol1").feature("s1").feature("i1").set("itrestart", 50);
    model.sol("sol1").feature("s1").feature("i1").set("rhob", 400);
    model.sol("sol1").feature("s1").feature("i1").set("maxlinit", 1000);
    model.sol("sol1").feature("s1").feature("i1").set("nlinnormuse", "on");
    model.sol("sol1").feature("s1").feature("i1").label("AMG\uff0c\u6d53\u5ea6 (tds)");
    model.sol("sol1").feature("s1").feature("i1").create("mg1", "Multigrid");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("prefun", "saamg");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("mgcycle", "v");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("maxcoarsedof", 50000);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("strconn", 0.01);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("nullspace", "constant");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("usesmooth", false);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("saamgcompwise", true);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("loweramg", true);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("compactaggregation", false);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").create("sl1", "SORLine");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1").set("iter", 1);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1").set("linerelax", 0.7);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1")
         .set("linealgorithm", "mesh");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1")
         .set("linemethod", "coupled");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1").set("seconditer", 1);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1").set("relax", 0.5);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").create("sl1", "SORLine");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1").set("iter", 1);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1").set("linerelax", 0.7);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1")
         .set("linealgorithm", "mesh");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1")
         .set("linemethod", "coupled");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1").set("seconditer", 1);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1").set("relax", 0.5);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s1").feature("fc1").set("linsolver", "d1");
    model.sol("sol1").feature("s1").feature("fc1").set("dtech", "auto");
    model.sol("sol1").feature("s1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("s1").feature("fc1").set("initstep", 0.01);
    model.sol("sol1").feature("s1").feature("fc1").set("minstep", 1.0E-6);
    model.sol("sol1").feature("s1").feature().remove("fcDef");
    model.sol("sol1").feature("s1").feature().remove("seDef");
    model.sol("sol1").create("su1", "StoreSolution");
    model.sol("sol1").feature("su1").set("sol", "sol2");
    model.sol("sol1").feature("su1").label("\u89e3\u5b58\u50a8 1");
    model.sol("sol1").create("st2", "StudyStep");
    model.sol("sol1").feature("st2").set("study", "std1");
    model.sol("sol1").feature("st2").set("studystep", "stat2");

    model.study("std1").feature("stat2").set("initsoluse", "sol2");

    model.sol("sol1").create("v2", "Variables");
    model.sol("sol1").feature("v2").set("initmethod", "sol");
    model.sol("sol1").feature("v2").set("initsol", "sol1");
    model.sol("sol1").feature("v2").set("initsoluse", "sol2");
    model.sol("sol1").feature("v2").set("notsolmethod", "sol");
    model.sol("sol1").feature("v2").set("notsol", "sol1");
    model.sol("sol1").feature("v2").set("control", "stat2");
    model.sol("sol1").create("s2", "Stationary");
    model.sol("sol1").feature("s2").set("stol", 0.001);
    model.sol("sol1").feature("s2").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("s2").create("seDef", "Segregated");
    model.sol("sol1").feature("s2").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("s2").feature("fc1").set("dtech", "auto");
    model.sol("sol1").feature("s2").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("s2").feature("fc1").set("initstep", 0.01);
    model.sol("sol1").feature("s2").feature("fc1").set("minstep", 1.0E-6);
    model.sol("sol1").feature("s2").create("d1", "Direct");
    model.sol("sol1").feature("s2").feature("d1").set("linsolver", "mumps");
    model.sol("sol1").feature("s2").feature("d1").set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s2").feature("d1").label("Direct (\u5df2\u5408\u5e76)");
    model.sol("sol1").feature("s2").create("i1", "Iterative");
    model.sol("sol1").feature("s2").feature("i1").set("linsolver", "gmres");
    model.sol("sol1").feature("s2").feature("i1").set("prefuntype", "left");
    model.sol("sol1").feature("s2").feature("i1").set("itrestart", 50);
    model.sol("sol1").feature("s2").feature("i1").set("rhob", 400);
    model.sol("sol1").feature("s2").feature("i1").set("maxlinit", 1000);
    model.sol("sol1").feature("s2").feature("i1").set("nlinnormuse", "on");
    model.sol("sol1").feature("s2").feature("i1").label("AMG\uff0c\u6d53\u5ea6 (tds)");
    model.sol("sol1").feature("s2").feature("i1").create("mg1", "Multigrid");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").set("prefun", "saamg");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").set("mgcycle", "v");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").set("maxcoarsedof", 50000);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").set("strconn", 0.01);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").set("nullspace", "constant");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").set("usesmooth", false);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").set("saamgcompwise", true);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").set("loweramg", true);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").set("compactaggregation", false);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").create("sl1", "SORLine");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sl1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sl1").set("iter", 1);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sl1").set("linerelax", 0.7);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sl1")
         .set("linealgorithm", "mesh");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sl1")
         .set("linemethod", "coupled");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sl1").set("seconditer", 1);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sl1").set("relax", 0.5);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").create("sl1", "SORLine");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").feature("sl1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").feature("sl1").set("iter", 1);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").feature("sl1").set("linerelax", 0.7);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").feature("sl1")
         .set("linealgorithm", "mesh");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").feature("sl1")
         .set("linemethod", "coupled");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").feature("sl1").set("seconditer", 1);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").feature("sl1").set("relax", 0.5);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s2").create("i2", "Iterative");
    model.sol("sol1").feature("s2").feature("i2").set("linsolver", "gmres");
    model.sol("sol1").feature("s2").feature("i2").set("prefuntype", "left");
    model.sol("sol1").feature("s2").feature("i2").set("itrestart", 50);
    model.sol("sol1").feature("s2").feature("i2").set("rhob", 20);
    model.sol("sol1").feature("s2").feature("i2").set("maxlinit", 10000);
    model.sol("sol1").feature("s2").feature("i2").set("nlinnormuse", "on");
    model.sol("sol1").feature("s2").feature("i2").label("AMG\uff0c\u4f20\u70ed\u53d8\u91cf (ht)");
    model.sol("sol1").feature("s2").feature("i2").create("mg1", "Multigrid");
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").set("prefun", "saamg");
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").set("mgcycle", "v");
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").set("maxcoarsedof", 50000);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").set("strconn", 0.01);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").set("nullspace", "constant");
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").set("usesmooth", false);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").set("saamgcompwise", true);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").set("loweramg", true);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").set("compactaggregation", false);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("pr").create("so1", "SOR");
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("pr").feature("so1").set("iter", 2);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("pr").feature("so1").set("relax", 0.9);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("po").create("so1", "SOR");
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("po").feature("so1").set("iter", 2);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("po").feature("so1").set("relax", 0.9);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s2").feature("fc1").set("linsolver", "d1");
    model.sol("sol1").feature("s2").feature("fc1").set("dtech", "auto");
    model.sol("sol1").feature("s2").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("s2").feature("fc1").set("initstep", 0.01);
    model.sol("sol1").feature("s2").feature("fc1").set("minstep", 1.0E-6);
    model.sol("sol1").feature("s2").feature().remove("fcDef");
    model.sol("sol1").feature("s2").feature().remove("seDef");

    model.result().dataset("dset2").set("solution", "sol2");

    model.sol("sol1").feature("v2").set("notsolnum", "auto");
    model.sol("sol1").feature("v2").set("notsolvertype", "solnum");

    model.study("std1").feature("stat2").set("notsolnum", "auto");
    model.study("std1").feature("stat2").set("notsolvertype", "solnum");
    model.study("std1").feature("stat2").set("notsolnumhide", "off");
    model.study("std1").feature("stat2").set("notstudyhide", "off");
    model.study("std1").feature("stat2").set("notsolhide", "off");

    model.sol("sol1").attach("std1");

    model.batch().remove("pd1");

    model.component("comp1").probe("pdom1").genResult("none");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().dataset().create("mir2", "Mirror2D");
    model.result().dataset("mir2").set("data", "grid1");
    model.result().dataset().create("rev3", "Revolve2D");
    model.result().dataset("rev3").set("data", "grid1");
    model.result().dataset("rev3").set("startangle", -90);
    model.result().dataset("rev3").set("revangle", 225);
    model.result().dataset("grid1").set("function", "dnn1");
    model.result().create("pg14", "PlotGroup3D");
    model.result("pg14").run();
    model.result("pg14").create("surf1", "Surface");
    model.result("pg14").feature("surf1").set("expr", "T");
    model.result("pg14").feature("surf1").set("colortable", "ThermalLight");
    model.result("pg14").run();
    model.result().create("pg15", "PlotGroup3D");
    model.result("pg15").run();
    model.result("pg15").set("data", "rev3");
    model.result("pg15").set("view", "view3");
    model.result("pg15").create("surf1", "Surface");
    model.result("pg15").feature("surf1").set("expr", "dnn1_1(x1,x2,E,ke,dHrx)");
    model.result("pg15").feature("surf1").set("colortable", "ThermalLight");
    model.result("pg15").run();
    model.result().create("pg16", "PlotGroup3D");
    model.result("pg16").run();
    model.result("pg16").create("surf1", "Surface");
    model.result("pg16").feature("surf1").set("expr", "xA");
    model.result("pg16").feature("surf1").set("rangecoloractive", true);
    model.result("pg16").feature("surf1").set("rangecolormax", 1);
    model.result("pg16").run();
    model.result().create("pg17", "PlotGroup3D");
    model.result("pg17").run();
    model.result("pg17").set("data", "rev3");
    model.result("pg17").set("view", "view3");
    model.result("pg17").create("surf1", "Surface");
    model.result("pg17").feature("surf1").set("expr", "dnn1_2(x1,x2,E,ke,dHrx)");
    model.result("pg17").feature("surf1").set("rangecoloractive", true);
    model.result("pg17").feature("surf1").set("rangecolormax", 1);
    model.result("pg17").run();

    model.component("comp1").mesh("mesh1").stat().selection().geom(2);
    model.component("comp1").mesh("mesh1").stat().selection().set(1);
    model.component("comp1").mesh("mesh1").stat().selection().geom(2);
    model.component("comp1").mesh("mesh1").stat().selection().set(1);

    model.sol("sol1").study("std1");

    model.study("std1").feature("stat2").set("notsolnum", "auto");
    model.study("std1").feature("stat2").set("notsolvertype", "solnum");
    model.study("std1").feature("stat2").set("notsolnumhide", "off");
    model.study("std1").feature("stat2").set("notstudyhide", "off");
    model.study("std1").feature("stat2").set("notsolhide", "off");

    model.sol("sol2").copySolution("sol5");

    model.study("std1").feature("stat2").set("notlistsolnum", 1);
    model.study("std1").feature("stat2").set("notsolnum", "auto");
    model.study("std1").feature("stat2").set("listsolnum", 1);
    model.study("std1").feature("stat2").set("solnum", "auto");

    model.result().dataset("dset2").set("solution", "none");

    model.sol("sol1").feature().remove("s2");
    model.sol("sol1").feature().remove("v2");
    model.sol("sol1").feature().remove("st2");
    model.sol("sol1").feature().remove("su1");
    model.sol("sol1").feature().remove("s1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol5").copySolution("sol2");
    model.sol().remove("sol5");
    model.sol("sol2").label("\u89e3\u5b58\u50a8 1");

    model.result().dataset().remove("dset6");

    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "stat");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").set("control", "stat");
    model.sol("sol1").create("s1", "Stationary");
    model.sol("sol1").feature("s1").set("stol", 0.001);
    model.sol("sol1").feature("s1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("s1").create("seDef", "Segregated");
    model.sol("sol1").feature("s1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("s1").feature("fc1").set("dtech", "auto");
    model.sol("sol1").feature("s1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("s1").feature("fc1").set("initstep", 0.01);
    model.sol("sol1").feature("s1").feature("fc1").set("minstep", 1.0E-6);
    model.sol("sol1").feature("s1").create("d1", "Direct");
    model.sol("sol1").feature("s1").feature("d1").label("Direct (\u5df2\u5408\u5e76)");
    model.sol("sol1").feature("s1").create("i1", "Iterative");
    model.sol("sol1").feature("s1").feature("i1").set("linsolver", "gmres");
    model.sol("sol1").feature("s1").feature("i1").set("prefuntype", "left");
    model.sol("sol1").feature("s1").feature("i1").set("itrestart", 50);
    model.sol("sol1").feature("s1").feature("i1").set("rhob", 400);
    model.sol("sol1").feature("s1").feature("i1").set("maxlinit", 1000);
    model.sol("sol1").feature("s1").feature("i1").set("nlinnormuse", "on");
    model.sol("sol1").feature("s1").feature("i1").label("AMG\uff0c\u6d53\u5ea6 (tds)");
    model.sol("sol1").feature("s1").feature("i1").create("mg1", "Multigrid");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("prefun", "saamg");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("mgcycle", "v");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("maxcoarsedof", 50000);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("strconn", 0.01);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("nullspace", "constant");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("usesmooth", false);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("saamgcompwise", true);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("loweramg", true);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("compactaggregation", false);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").create("sl1", "SORLine");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1").set("iter", 1);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1").set("linerelax", 0.7);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1")
         .set("linealgorithm", "mesh");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1")
         .set("linemethod", "coupled");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1").set("seconditer", 1);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").feature("sl1").set("relax", 0.5);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").create("sl1", "SORLine");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1").set("iter", 1);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1").set("linerelax", 0.7);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1")
         .set("linealgorithm", "mesh");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1")
         .set("linemethod", "coupled");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1").set("seconditer", 1);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").feature("sl1").set("relax", 0.5);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s1").feature("fc1").set("linsolver", "d1");
    model.sol("sol1").feature("s1").feature("fc1").set("dtech", "auto");
    model.sol("sol1").feature("s1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("s1").feature("fc1").set("initstep", 0.01);
    model.sol("sol1").feature("s1").feature("fc1").set("minstep", 1.0E-6);
    model.sol("sol1").feature("s1").feature().remove("fcDef");
    model.sol("sol1").feature("s1").feature().remove("seDef");
    model.sol("sol1").create("su1", "StoreSolution");
    model.sol("sol1").feature("su1").set("sol", "sol2");
    model.sol("sol1").feature("su1").label("\u89e3\u5b58\u50a8 1");
    model.sol("sol1").create("st2", "StudyStep");
    model.sol("sol1").feature("st2").set("study", "std1");
    model.sol("sol1").feature("st2").set("studystep", "stat2");

    model.study("std1").feature("stat2").set("initsoluse", "sol2");

    model.sol("sol1").create("v2", "Variables");
    model.sol("sol1").feature("v2").set("initmethod", "sol");
    model.sol("sol1").feature("v2").set("initsol", "sol1");
    model.sol("sol1").feature("v2").set("initsoluse", "sol2");
    model.sol("sol1").feature("v2").set("notsolmethod", "sol");
    model.sol("sol1").feature("v2").set("notsol", "sol1");
    model.sol("sol1").feature("v2").set("control", "stat2");
    model.sol("sol1").create("s2", "Stationary");
    model.sol("sol1").feature("s2").set("stol", 0.001);
    model.sol("sol1").feature("s2").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("s2").create("seDef", "Segregated");
    model.sol("sol1").feature("s2").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("s2").feature("fc1").set("dtech", "auto");
    model.sol("sol1").feature("s2").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("s2").feature("fc1").set("initstep", 0.01);
    model.sol("sol1").feature("s2").feature("fc1").set("minstep", 1.0E-6);
    model.sol("sol1").feature("s2").create("d1", "Direct");
    model.sol("sol1").feature("s2").feature("d1").set("linsolver", "mumps");
    model.sol("sol1").feature("s2").feature("d1").set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s2").feature("d1").label("Direct (\u5df2\u5408\u5e76)");
    model.sol("sol1").feature("s2").create("i1", "Iterative");
    model.sol("sol1").feature("s2").feature("i1").set("linsolver", "gmres");
    model.sol("sol1").feature("s2").feature("i1").set("prefuntype", "left");
    model.sol("sol1").feature("s2").feature("i1").set("itrestart", 50);
    model.sol("sol1").feature("s2").feature("i1").set("rhob", 400);
    model.sol("sol1").feature("s2").feature("i1").set("maxlinit", 1000);
    model.sol("sol1").feature("s2").feature("i1").set("nlinnormuse", "on");
    model.sol("sol1").feature("s2").feature("i1").label("AMG\uff0c\u6d53\u5ea6 (tds)");
    model.sol("sol1").feature("s2").feature("i1").create("mg1", "Multigrid");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").set("prefun", "saamg");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").set("mgcycle", "v");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").set("maxcoarsedof", 50000);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").set("strconn", 0.01);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").set("nullspace", "constant");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").set("usesmooth", false);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").set("saamgcompwise", true);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").set("loweramg", true);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").set("compactaggregation", false);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").create("sl1", "SORLine");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sl1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sl1").set("iter", 1);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sl1").set("linerelax", 0.7);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sl1")
         .set("linealgorithm", "mesh");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sl1")
         .set("linemethod", "coupled");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sl1").set("seconditer", 1);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("pr").feature("sl1").set("relax", 0.5);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").create("sl1", "SORLine");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").feature("sl1")
         .set("linesweeptype", "ssor");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").feature("sl1").set("iter", 1);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").feature("sl1").set("linerelax", 0.7);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").feature("sl1")
         .set("linealgorithm", "mesh");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").feature("sl1")
         .set("linemethod", "coupled");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").feature("sl1").set("seconditer", 1);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("po").feature("sl1").set("relax", 0.5);
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol1").feature("s2").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s2").create("i2", "Iterative");
    model.sol("sol1").feature("s2").feature("i2").set("linsolver", "gmres");
    model.sol("sol1").feature("s2").feature("i2").set("prefuntype", "left");
    model.sol("sol1").feature("s2").feature("i2").set("itrestart", 50);
    model.sol("sol1").feature("s2").feature("i2").set("rhob", 20);
    model.sol("sol1").feature("s2").feature("i2").set("maxlinit", 10000);
    model.sol("sol1").feature("s2").feature("i2").set("nlinnormuse", "on");
    model.sol("sol1").feature("s2").feature("i2").label("AMG\uff0c\u4f20\u70ed\u53d8\u91cf (ht)");
    model.sol("sol1").feature("s2").feature("i2").create("mg1", "Multigrid");
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").set("prefun", "saamg");
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").set("mgcycle", "v");
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").set("maxcoarsedof", 50000);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").set("strconn", 0.01);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").set("nullspace", "constant");
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").set("usesmooth", false);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").set("saamgcompwise", true);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").set("loweramg", true);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").set("compactaggregation", false);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("pr").create("so1", "SOR");
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("pr").feature("so1").set("iter", 2);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("pr").feature("so1").set("relax", 0.9);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("po").create("so1", "SOR");
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("po").feature("so1").set("iter", 2);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("po").feature("so1").set("relax", 0.9);
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol1").feature("s2").feature("i2").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s2").feature("fc1").set("linsolver", "d1");
    model.sol("sol1").feature("s2").feature("fc1").set("dtech", "auto");
    model.sol("sol1").feature("s2").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("s2").feature("fc1").set("initstep", 0.01);
    model.sol("sol1").feature("s2").feature("fc1").set("minstep", 1.0E-6);
    model.sol("sol1").feature("s2").feature().remove("fcDef");
    model.sol("sol1").feature("s2").feature().remove("seDef");

    model.result().dataset("dset2").set("solution", "sol2");

    model.sol("sol1").feature("v2").set("notsolnum", "auto");
    model.sol("sol1").feature("v2").set("notsolvertype", "solnum");

    model.study("std1").feature("stat2").set("notsolnum", "auto");
    model.study("std1").feature("stat2").set("notsolvertype", "solnum");
    model.study("std1").feature("stat2").set("notsolnumhide", "off");
    model.study("std1").feature("stat2").set("notstudyhide", "off");
    model.study("std1").feature("stat2").set("notsolhide", "off");

    model.sol("sol1").attach("std1");

    model.component("comp1").probe("pdom1").genResult("none");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().dataset("cln1").setIndex("genpoints", "L", 0, 1);
    model.result().dataset("cln1").setIndex("genpoints", "L", 1, 1);
    model.result().dataset("cln1").set("genparaactive", false);
    model.result("pg7").run();
    model.result().dataset().create("grid2", "Grid1D");
    model.result().dataset("grid2").set("source", "data");
    model.result().dataset("grid2").set("par1", "x1");
    model.result().dataset("grid2").set("parmax1", 0.1);

    return model;
  }

  public static Model run4(Model model) {
    model.result().dataset("grid2").set("source", "function");
    model.result().dataset("grid2").set("function", "dnn1");
    model.result("pg7").run();
    model.result("pg7").feature("lngr1").setIndex("legends", "Outlet (computed)", 0);
    model.result("pg7").run();
    model.result("pg7").create("lngr2", "LineGraph");
    model.result("pg7").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg7").feature("lngr2").set("linewidth", "preference");
    model.result("pg7").feature("lngr2").set("data", "grid2");
    model.result("pg7").feature("lngr2").set("expr", "dnn1_1(x1,L,E,ke,dHrx)");
    model.result("pg7").feature("lngr2").set("linestyle", "dashed");
    model.result("pg7").feature("lngr2").set("linecolor", "blue");
    model.result("pg7").feature("lngr2").set("legend", true);
    model.result("pg7").feature("lngr2").set("legendmethod", "manual");
    model.result("pg7").feature("lngr2").setIndex("legends", "Outlet (preview)", 0);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg8").run();
    model.result("pg8").create("lngr2", "LineGraph");
    model.result("pg8").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg8").feature("lngr2").set("linewidth", "preference");
    model.result("pg8").feature("lngr2").set("data", "grid2");
    model.result("pg8").feature("lngr2").set("expr", "dnn1_2(x1,L,E,ke,dHrx)");
    model.result("pg8").feature("lngr2").set("linestyle", "dashed");
    model.result("pg8").feature("lngr2").set("linecolor", "blue");
    model.result("pg8").feature("lngr2").set("legend", true);
    model.result("pg8").feature("lngr2").set("legendmethod", "manual");
    model.result("pg8").feature("lngr2").setIndex("legends", "Outlet (preview)", 0);
    model.result("pg8").run();
    model.result("pg8").set("axislimits", true);
    model.result("pg8").set("xmin", 0);
    model.result("pg8").set("xmax", 0.1);
    model.result("pg8").set("ymin", 0);
    model.result("pg8").set("ymax", 1);
    model.result("pg8").set("legendpos", "lowerleft");
    model.result("pg8").run();
    model.result("pg14").run();
    model.result("pg14").set("edges", false);
    model.result("pg14").set("titletype", "manual");
    model.result("pg14").run();
    model.result("pg15").run();
    model.result("pg15").set("titletype", "manual");
    model.result("pg16").run();
    model.result("pg16").set("edges", false);
    model.result("pg16").set("titletype", "manual");
    model.result("pg17").run();
    model.result("pg17").set("titletype", "manual");
    model.result("pg14").run();

    model.title("\u7ba1\u5f0f\u53cd\u5e94\u5668\u4ee3\u7406\u6a21\u578b");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u4ee3\u7406\u6a21\u578b\uff08\u800c\u975e\u6709\u9650\u5143\u6a21\u578b\uff09\u6765\u63d0\u9ad8\u7ba1\u5f0f\u53cd\u5e94\u5668 App \u7684\u8ba1\u7b97\u901f\u5ea6\u3002\u8fd9\u662f\u4e00\u79cd\u66f4\u7b80\u5355\u4e14\u901a\u5e38\u8ba1\u7b97\u6210\u672c\u4e5f\u66f4\u4f4e\u7684\u6a21\u578b\uff0c\u7528\u4e8e\u8fd1\u4f3c\u6a21\u62df\u8f83\u590d\u6742\u6a21\u578b\uff08\u901a\u5e38\u8ba1\u7b97\u6210\u672c\u66f4\u9ad8\uff09\u7684\u884c\u4e3a\uff1b\u53ef\u4ee5\u5b9e\u73b0\u66f4\u5feb\u7684\u6a21\u578b\u8ba1\u7b97\u901f\u5ea6\uff0c\u4e3a App \u7528\u6237\u63d0\u4f9b\u66f4\u5177\u4ea4\u4e92\u6027\u7684\u7528\u6237\u4f53\u9a8c\uff0c\u540c\u65f6\u4e5f\u66f4\u5bb9\u6613\u5728\u7ec4\u7ec7\u5185\u63a8\u5e7f\u4eff\u771f\u7684\u4f7f\u7528\u3002");

    model.label("tubular_reactor_surrogate.mph");

    model.result("pg14").run();

    model.setExpectedComputationTime("5 s");

    model.result("pg8").run();
    model.result("pg8").set("title", "\u5f84\u5411\u8f6c\u5316\u7387\u66f2\u7ebf");
    model.result().report().create("rpt1", "Report");
    model.result().report("rpt1").set("templatesource", "brief");
    model.result().report("rpt1").set("format", "docx");
    model.result().report("rpt1").set("filename", "user:///tubular.docx");
    model.result().report("rpt1").set("pagebreaklevel", "2");
    model.result().report("rpt1").set("enumlevel", "none");
    model.result().report("rpt1").set("imagesize", "large");
    model.result().report("rpt1").feature().create("tp1", "TitlePage");
    model.result().report("rpt1").feature("tp1").label("\u5e26\u5939\u5957\u7684\u7ba1\u5f0f\u53cd\u5e94\u5668");
    model.result().report("rpt1").feature("tp1").set("includeauthor", false);
    model.result().report("rpt1").feature("tp1").set("includecompany", false);
    model.result().report("rpt1").feature("tp1").set("includeversion", false);
    model.result().report("rpt1").feature("tp1").set("reportdate", "none");
    model.result().report("rpt1").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec1").label("\u8f6f\u4ef6\u4fe1\u606f");
    model.result().report("rpt1").feature("sec1").feature().create("root1", "Model");
    model.result().report("rpt1").feature("sec1").feature("root1").label("\u5173\u4e8e\u8f6f\u4ef6");
    model.result().report("rpt1").feature("sec1").feature("root1").set("includeunitsystem", true);
    model.result().report("rpt1").feature("sec1").feature().create("std1", "Study");
    model.result().report("rpt1").feature("sec1").feature("std1").setIndex("children", false, 1, 1);
    model.result().report("rpt1").feature("sec1").feature("std1").setIndex("children", false, 2, 1);
    model.result().report("rpt1").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec2").label("\u6a21\u578b\u53c2\u6570");
    model.result().report("rpt1").feature("sec2").feature().create("param1", "Parameter");
    model.result().report("rpt1").feature("sec2").feature("param1")
         .label("\u5185\u5d4c\u6a21\u578b\u4e2d\u7684\u53c2\u6570");
    model.result().report("rpt1").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec3").label("\u7ed3\u679c");
    model.result().report("rpt1").feature("sec3").feature().create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec3").feature("pg1").label("\u6e29\u5ea6\u8868\u9762");
    model.result().report("rpt1").feature("sec3").feature("pg1").set("noderef", "pg5");
    model.result().report("rpt1").feature("sec3").feature().create("pg2", "PlotGroup");
    model.result().report("rpt1").feature("sec3").feature("pg2").label("\u8f6c\u5316\u7387\u8868\u9762");
    model.result().report("rpt1").feature("sec3").feature("pg2").set("noderef", "pg6");
    model.result().report("rpt1").feature("sec3").feature().create("pg3", "PlotGroup");
    model.result().report("rpt1").feature("sec3").feature("pg3").label("\u6e29\u5ea6\u5206\u5e03");
    model.result().report("rpt1").feature("sec3").feature("pg3").set("noderef", "pg7");
    model.result().report("rpt1").feature("sec3").feature().create("pg4", "PlotGroup");
    model.result().report("rpt1").feature("sec3").feature("pg4").label("\u8f6c\u5316\u7387\u66f2\u7ebf");
    model.result().report("rpt1").feature("sec3").feature("pg4").set("noderef", "pg8");

    model.title("\u7ba1\u5f0f\u53cd\u5e94\u5668\u4ee3\u7406\u6a21\u578b App");

    model
         .description("\u8fd9\u4e2a\u7ba1\u5f0f\u53cd\u5e94\u5668 App \u53ca\u5176\u76f8\u5173\u7684\u5d4c\u5165\u6a21\u578b\u6f14\u793a\u5982\u4f55\u901a\u8fc7\u4f7f\u7528\u4ee3\u7406\u6a21\u578b\uff08\u800c\u975e\u5b8c\u5168\u6210\u719f\u7684\u6709\u9650\u5143\u6a21\u578b\uff09\u6765\u63d0\u9ad8\u8ba1\u7b97\u901f\u5ea6\u3002\u8fd9\u662f\u4e00\u79cd\u66f4\u7b80\u5355\u4e14\u901a\u5e38\u8ba1\u7b97\u6210\u672c\u4e5f\u66f4\u4f4e\u7684\u6a21\u578b\uff0c\u7528\u4e8e\u8fd1\u4f3c\u6a21\u62df\u8f83\u590d\u6742\u6a21\u578b\uff08\u901a\u5e38\u8ba1\u7b97\u6210\u672c\u66f4\u9ad8\uff09\u7684\u884c\u4e3a\uff1b\u53ef\u4ee5\u5b9e\u73b0\u66f4\u5feb\u7684\u6a21\u578b\u8ba1\u7b97\u901f\u5ea6\uff0c\u4e3a App \u7528\u6237\u63d0\u4f9b\u66f4\u5177\u4ea4\u4e92\u6027\u7684\u7528\u6237\u4f53\u9a8c\uff0c\u540c\u65f6\u4e5f\u66f4\u5bb9\u6613\u5728\u7ec4\u7ec7\u5185\u63a8\u5e7f\u4eff\u771f\u7684\u4f7f\u7528\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("tubular_reactor_surrogate.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    model = run3(model);
    run4(model);
  }

}
