/*
 * lightning_surge_wind_farm.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:50 by COMSOL 6.3.0.290. */
public class lightning_surge_wind_farm {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electric_Discharge_Module\\Discharge-Induced_Effects");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("temw", "TransientElectromagneticWaves", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/temw", true);

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").label("\u98ce\u529b\u6da1\u8f6e\u673a\u5854\u67b6");
    model.component("comp1").geom("geom1").feature("imp1")
         .set("filename", "lightning_surge_wind_farm_turbine_tower.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").run("imp1");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u94a2\u4f53");
    model.component("comp1").geom("geom1").feature("imp1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").create("imp2", "Import");
    model.component("comp1").geom("geom1").feature("imp2").label("\u98ce\u529b\u6da1\u8f6e\u673a\u53f6\u7247");
    model.component("comp1").geom("geom1").feature("imp2")
         .set("filename", "lightning_surge_wind_farm_turbine_blades.mphbin");
    model.component("comp1").geom("geom1").feature("imp2").importData();
    model.component("comp1").geom("geom1").run("imp2");
    model.component("comp1").geom("geom1").create("imp3", "Import");
    model.component("comp1").geom("geom1").feature("imp3").label("\u5185\u5e26");
    model.component("comp1").geom("geom1").feature("imp3")
         .set("filename", "lightning_surge_wind_farm_inner_strip.mphbin");
    model.component("comp1").geom("geom1").feature("imp3").importData();
    model.component("comp1").geom("geom1").run("imp3");
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("\u4e0b\u5bfc\u7ebf");
    model.component("comp1").geom("geom1").feature("imp3").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").create("imp4", "Import");
    model.component("comp1").geom("geom1").feature("imp4").label("\u652f\u6491\u7ed3\u6784");
    model.component("comp1").geom("geom1").feature("imp4")
         .set("filename", "lightning_surge_wind_farm_turbine_support1.mphbin");
    model.component("comp1").geom("geom1").feature("imp4").importData();
    model.component("comp1").geom("geom1").run("imp4");
    model.component("comp1").geom("geom1").feature("imp4").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").create("imp5", "Import");
    model.component("comp1").geom("geom1").feature("imp5").label("\u6d6e\u52a8\u652f\u6491\u7ed3\u6784");
    model.component("comp1").geom("geom1").feature("imp5")
         .set("filename", "lightning_surge_wind_farm_turbine_support2.mphbin");
    model.component("comp1").geom("geom1").feature("imp5").importData();
    model.component("comp1").geom("geom1").run("imp5");
    model.component("comp1").geom("geom1").feature("imp5").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").create("imp6", "Import");
    model.component("comp1").geom("geom1").feature("imp6").label("\u6c34\u9762\u548c\u6d77\u5e8a\u8868\u9762");
    model.component("comp1").geom("geom1").feature("imp6").set("filename", "lightning_surge_wind_farm_water.mphbin");
    model.component("comp1").geom("geom1").feature("imp6").importData();
    model.component("comp1").geom("geom1").run("imp6");
    model.component("comp1").geom("geom1").create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("mov1").selection("input").set("imp5");
    model.component("comp1").geom("geom1").feature("mov1").set("displx", "300/sqrt(3)");
    model.component("comp1").geom("geom1").run("mov1");
    model.component("comp1").geom("geom1").create("mov2", "Move");
    model.component("comp1").geom("geom1").feature("mov2").selection("input").set("imp3");
    model.component("comp1").geom("geom1").feature("mov2").set("displz", -200);
    model.component("comp1").geom("geom1").run("mov2");
    model.component("comp1").geom("geom1").create("mov3", "Move");
    model.component("comp1").geom("geom1").feature("mov3").selection("input").set("imp4");
    model.component("comp1").geom("geom1").feature("mov3").set("displx", "-150/sqrt(3)");
    model.component("comp1").geom("geom1").feature("mov3").set("disply", 150);
    model.component("comp1").geom("geom1").run("mov3");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", 5);
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 50);
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"-150/sqrt(3)", "0", "0"});
    model.component("comp1").geom("geom1").feature("cyl1").setIndex("pos", -150, 1);
    model.component("comp1").geom("geom1").feature("cyl1").setIndex("pos", -50, 2);
    model.component("comp1").geom("geom1").feature("cyl1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("copy1").selection("input").set("imp1", "imp2", "mov2");
    model.component("comp1").geom("geom1").feature("copy1").set("displx", "-150/sqrt(3)");
    model.component("comp1").geom("geom1").feature("copy1").set("keep", false);
    model.component("comp1").geom("geom1").feature("copy1").set("displx", "-150/sqrt(3) -150/sqrt(3) 300/sqrt(3)");
    model.component("comp1").geom("geom1").feature("copy1").set("disply", "-150 150 0");
    model.component("comp1").geom("geom1").run("copy1");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").label("\u5148\u5bfc\u901a\u9053");
    model.component("comp1").geom("geom1").feature("pol1")
         .set("table", new String[][]{{"0.00", "0.00", "600.00"}, 
         {"6.72", "0.90", "597.01"}, 
         {"3.66", "2.71", "582.19"}, 
         {"1.93", "2.85", "555.09"}, 
         {"14.89", "5.42", "551.82"}, 
         {"0.54", "12.96", "535.17"}, 
         {"10.96", "8.97", "517.88"}, 
         {"4.03", "11.30", "500.21"}, 
         {"9.11", "1.70", "485.50"}, 
         {"0.00", "0.00", "450.00"}, 
         {"-20.00", "20.00", "430.00"}, 
         {"-19.59", "31.03", "418.08"}, 
         {"-23.20", "47.70", "415.13"}, 
         {"-26.27", "46.41", "401.96"}, 
         {"-28.99", "60.81", "391.31"}, 
         {"-22.82", "66.38", "375.92"}, 
         {"-30.86", "74.42", "352.72"}, 
         {"-32.87", "80.59", "337.31"}, 
         {"-38.49", "77.90", "325.51"}, 
         {"-35.75", "90.34", "316.80"}, 
         {"-49.24", "91.61", "296.76"}, 
         {"-51.38", "105.51", "288.77"}, 
         {"-53.70", "108.07", "271.62"}, 
         {"-56.70", "110.78", "260.98"}, 
         {"-70.00", "120.00", "230.00"}});
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").selection().create("csel3", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel3").label("\u96f7\u51fb\u901a\u9053");
    model.component("comp1").geom("geom1").feature("pol1").set("contributeto", "csel3");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").label("\u6700\u7ec8\u96f7\u51fb");
    model.component("comp1").geom("geom1").feature("ls1").selection("vertex1").set("pol1", 1);
    model.component("comp1").geom("geom1").feature("ls1").selection("vertex2").set("copy1(5)", 273);
    model.component("comp1").geom("geom1").feature("ls1").set("contributeto", "csel3");
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", 600);
    model.component("comp1").geom("geom1").feature("cyl2").set("h", 1000);
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new int[]{0, 0, -300});
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("imp6");
    model.component("comp1").geom("geom1").feature("par1").selection("tool").set("cyl2");
    model.component("comp1").geom("geom1").feature("par1").set("keeptool", true);
    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("par1", 1, 2);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat1").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp1").material("mat1").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").label("Air");
    model.component("comp1").material("mat1").set("family", "air");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("molarmass", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("pressure");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp1").material("mat1").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat1").propertyGroup("idealGas").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("idealGas").addInput("pressure");
    model.component("comp1").material("mat1").materialType("nonSolid");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u6d77\u6c34");
    model.component("comp1").material("mat2").selection().set(2);
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"80"});
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("electricconductivity", new String[]{"4"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").selection().set(1);
    model.component("comp1").material("mat3").label("\u6d77\u5e8a");
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermittivity", new String[]{"15"});
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("electricconductivity", new String[]{"0.1"});

    model.component("comp1").physics("temw").prop("ShapeProperty").set("order_magneticvectorpotential", 1);
    model.component("comp1").physics("temw").create("edc1", "EdgeCurrent", 1);
    model.component("comp1").physics("temw").feature("edc1").selection().named("geom1_csel3_edg");
    model.component("comp1").physics("temw").feature("edc1").set("CurrentPulseType", "Lightning");
    model.component("comp1").physics("temw").feature("edc1").set("LightningPulseAmplitude", "20[kA]");
    model.component("comp1").physics("temw").feature("edc1").set("LightningPulseRiseTimeConstant", "0.8[us]");
    model.component("comp1").physics("temw").feature("edc1").set("LightningPulseDecayTimeConstant", "80[us]");
    model.component("comp1").physics("temw").feature("edc1").set("v_p", "c_const/3");
    model.component("comp1").physics("temw").feature("edc1").set("reverseDirection", true);

    model.func().create("Pulse1", "Analytic");
    model.func("Pulse1").set("args", "t");
    model.func("Pulse1").setIndex("argunit", "s", 0);
    model.func("Pulse1").set("fununit", "A");
    model.func("Pulse1")
         .set("expr", "1.0070493454179255*20[kA]*(-t/0.8[us])^10*exp(-t/80[us])/(1+(-t/0.8[us])^10) ");
    model.func("Pulse1").setIndex("plotargs", "7.999999999999999E-5", 0, 2);

    model.result().dataset().create("grid1", "Grid1D");
    model.result().dataset("grid1").set("source", "function");
    model.result().dataset("grid1").set("function", "all");
    model.result().dataset("grid1").set("par1", "t");
    model.result().dataset("grid1").set("parmax1", "7.999999999999999E-5");
    model.result().dataset("grid1").set("res1", 10000);
    model.result().dataset("grid1").set("distribution", "mixed");
    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "grid1");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u8fb9\u7535\u6d41\u8109\u51b2\u56fe");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "Pulse current (A)");
    model.result("pg1").set("windowtitle", "\u56fe\u5f62");
    model.result("pg1").set("window", "window1");
    model.result("pg1").create("fun1", "Function");
    model.result("pg1").feature("fun1").set("linewidth", "preference");
    model.result("pg1").feature("fun1").set("expr", "Pulse1(t[1/m][s])");
    model.result("pg1").feature("fun1").set("xdataexpr", "t");
    model.result("pg1").feature("fun1").set("xdataunit", "");
    model.result("pg1").feature("fun1").set("xdatadescractive", true);
    model.result("pg1").feature("fun1").set("xdatadescr", "Time (s)");
    model.result("pg1").feature("fun1").set("upperbound", "7.999999999999999E-5");
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result().dataset().remove("grid1");

    model.func().remove("Pulse1");

    model.component("comp1").physics("temw").create("pec2", "PerfectElectricConductor", 2);
    model.component("comp1").physics("temw").feature("pec2").selection().named("geom1_csel1_bnd");
    model.component("comp1").physics("temw").create("pec3", "PerfectElectricConductor", 2);
    model.component("comp1").physics("temw").feature("pec3").selection().set(667);
    model.component("comp1").physics("temw").create("pec4", "PerfectElectricConductor", 2);
    model.component("comp1").physics("temw").feature("pec4").selection().named("geom1_csel2_bnd");
    model.component("comp1").physics("temw").create("sctr1", "Scattering", 2);
    model.component("comp1").physics("temw").feature("sctr1").selection()
         .set(1, 2, 3, 4, 5, 7, 8, 10, 1262, 1263, 1264, 1265, 1266, 1267);

    model.component("comp1").view("view1").hideEntities().create("hide1");
    model.component("comp1").view("view1").hideEntities("hide1").geom("geom1", 2);
    model.component("comp1").view("view1").hideEntities("hide1").set(4, 7, 10, 1259, 1260);

    model.component("comp1").physics("temw").prop("MeshControl").set("PhysicsControlledMeshMaximumElementSize", 120);

    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 60);
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", 20);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", 40);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmin", 20);
    model.component("comp1").mesh("mesh1").feature("size2").set("hmax", 30);
    model.component("comp1").mesh("mesh1").feature("size2").set("hmin", 20);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tunit", "\u00b5s");
    model.study("std1").feature("time").set("tlist", "range(0,0.05,5)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("solvertype", "none");
    model.result("pg1").feature().create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("solvertype", "none");
    model.result("pg1").feature("mslc1").set("smooth", "internal");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("solvertype", "none");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("solvertype", "none");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("solvertype", "none");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("solvertype", "none");
    model.result("pg1").feature("mslc1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("mslc1").create("tran1", "Transparency");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").create("vol1", "Volume");
    model.result("pg2").feature("vol1").label("\u6d77\u6c34");
    model.result("pg2").feature("vol1").set("rangecoloractive", true);
    model.result("pg2").feature("vol1").set("rangecolormax", 1);
    model.result("pg2").feature("vol1").set("colortable", "JupiterAuroraBorealis");
    model.result("pg2").feature("vol1").create("sel1", "Selection");
    model.result("pg2").feature("vol1").feature("sel1").selection().set(2);
    model.result("pg2").run();
    model.result("pg2").feature("vol1").create("mtrl1", "MaterialAppearance");
    model.result("pg2").run();
    model.result("pg2").feature("vol1").feature("mtrl1").set("appearance", "custom");
    model.result("pg2").feature("vol1").feature("mtrl1").set("family", "water");
    model.result("pg2").feature("vol1").feature("mtrl1").set("useplotcolors", true);
    model.result("pg2").run();
    model.result("pg2").feature("vol1").create("tran1", "Transparency");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").create("vol2", "Volume");
    model.result("pg2").feature("vol2").label("\u6d77\u5e8a");
    model.result("pg2").feature("vol2").set("colortable", "GrayBody");
    model.result("pg2").feature("vol2").create("sel1", "Selection");
    model.result("pg2").feature("vol2").feature("sel1").selection().set(1);
    model.result("pg2").run();
    model.result("pg2").feature("vol2").create("mtrl1", "MaterialAppearance");
    model.result("pg2").run();
    model.result("pg2").feature("vol2").feature("mtrl1").set("appearance", "custom");
    model.result("pg2").feature("vol2").feature("mtrl1").set("family", "soil");
    model.result("pg2").feature("vol2").feature("mtrl1").set("useplotcolors", true);
    model.result("pg2").run();
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u98ce\u529b\u6da1\u8f6e\u673a\u673a\u8eab");
    model.result("pg2").feature("surf1").set("colortable", "Plasma");
    model.result("pg2").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg2").feature("surf1").create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection().named("geom1_csel1_bnd");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg2").feature("surf1").feature("mtrl1").set("family", "steelanodized");
    model.result("pg2").feature("surf1").feature("mtrl1").set("useplotcolors", true);
    model.result("pg2").run();
    model.result("pg2").create("surf2", "Surface");
    model.result("pg2").feature("surf2").label("\u4e0b\u5bfc\u7ebf");
    model.result("pg2").feature("surf2").create("sel1", "Selection");
    model.result("pg2").feature("surf2").feature("sel1").selection().named("geom1_csel2_bnd");
    model.result("pg2").run();
    model.result("pg2").feature("surf2").create("mtrl1", "MaterialAppearance");
    model.result("pg2").run();
    model.result("pg2").feature("surf2").feature("mtrl1").set("appearance", "custom");
    model.result("pg2").feature("surf2").feature("mtrl1").set("family", "gold");
    model.result("pg2").feature("surf2").feature("mtrl1").set("useplotcolors", true);
    model.result("pg2").run();
    model.result("pg2").create("line1", "Line");
    model.result("pg2").feature("line1").set("linetype", "tube");
    model.result("pg2").feature("line1").set("radiusexpr", "sqrt(z-175)");
    model.result("pg2").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg2").feature("line1").set("tuberadiusscale", 0.1);
    model.result("pg2").feature("line1").set("colorscalemode", "logarithmic");
    model.result("pg2").feature("line1").set("colortable", "Plasma");
    model.result("pg2").feature("line1").label("\u96f7\u51fb\u901a\u9053");
    model.result("pg2").feature("line1").create("sel1", "Selection");
    model.result("pg2").feature("line1").feature("sel1").selection().named("geom1_csel3_edg");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("line1").create("tran1", "Transparency");
    model.result("pg2").run();
    model.result("pg2").feature("line1").feature("tran1").set("transparency", 0.65);
    model.result("pg2").run();
    model.result("pg2").set("edges", false);
    model.result("pg2").create("vol3", "Volume");
    model.result("pg2").feature("vol3").label("\u53f6\u7247");
    model.result("pg2").feature("vol3").set("expr", "1");
    model.result("pg2").feature("vol3").create("sel1", "Selection");
    model.result("pg2").feature("vol3").feature("sel1").selection().set(4, 5, 22);
    model.result("pg2").run();
    model.result("pg2").feature("vol3").create("mtrl1", "MaterialAppearance");
    model.result("pg2").run();
    model.result("pg2").feature("vol3").feature("mtrl1").set("appearance", "custom");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("vol3").create("tran1", "Transparency");
    model.result("pg2").run();
    model.result("pg2").set("showlegends", false);

    model.component("comp1").view("view1").camera().set("zoomanglefull", 35);
    model.component("comp1").view("view1").camera().setIndex("position", 800, 0);
    model.component("comp1").view("view1").camera().setIndex("position", -900, 1);
    model.component("comp1").view("view1").camera().set("position", new int[]{800, -900, 900});
    model.component("comp1").view("view1").camera().setIndex("target", -4000, 0);
    model.component("comp1").view("view1").camera().set("target", new int[]{-4000, 5000, -4000});
    model.component("comp1").view("view1").camera().setIndex("up", -0.3, 0);
    model.component("comp1").view("view1").camera().setIndex("up", 0.35, 1);
    model.component("comp1").view("view1").camera().set("up", new double[]{-0.3, 0.35, 1});
    model.component("comp1").view("view1").camera().set("rotationpoint", new int[]{-50, 80, 15});
    model.component("comp1").view("view1").camera().setIndex("viewoffset", 0.05, 0);
    model.component("comp1").view("view1").camera().set("viewoffset", new double[]{0.05, 0.05});

    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").create("ptgr1", "PointGraph");
    model.result("pg3").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg3").feature("ptgr1").set("linewidth", "preference");
    model.result("pg3").feature("ptgr1").selection().set(1560, 1561, 2385);
    model.result("pg3").run();
    model.result("pg3").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg3").feature("ptgr1")
         .setIndex("legends", "\u90bb\u8fd1\u6da1\u8f6e\u673a\uff0c\u4f4d\u4e8e\u6d77\u5e8a\u4e0a", 0);
    model.result("pg3").feature("ptgr1").setIndex("legends", "\u906d\u53d7\u96f7\u51fb\u7684\u6da1\u8f6e\u673a", 1);
    model.result("pg3").feature("ptgr1").setIndex("legends", "\u90bb\u8fd1\u6da1\u8f6e\u673a\uff0c\u6d6e\u52a8", 2);
    model.result("pg3").run();
    model.result("pg3").feature("ptgr1").set("legend", true);
    model.result("pg3").run();

    model.title("\u6d77\u4e0a\u98ce\u7535\u573a\u7684\u96f7\u51fb\u6d6a\u6d8c\u5206\u6790");

    model
         .description("\u672c\u6a21\u578b\u5206\u6790\u6d77\u4e0a\u98ce\u7535\u573a\u7684\u96f7\u51fb\u6d6a\u6d8c\u73b0\u8c61\uff0c\u8ba1\u7b97\u4e86\u4e00\u53f0\u98ce\u529b\u53d1\u7535\u673a\u906d\u53d7 20\u00a0kA \u96f7\u51fb\u7535\u6d41\u51b2\u51fb\u65f6\uff0c\u76f8\u90bb\u98ce\u529b\u53d1\u7535\u673a\u7684\u611f\u5e94\u7535\u573a\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("lightning_surge_wind_farm.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
