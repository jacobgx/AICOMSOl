/*
 * flow_meter_piezoelectric_transducers.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:40 by COMSOL 6.3.0.290. */
public class flow_meter_piezoelectric_transducers {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Ultrasound");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("D", "5[mm]", "\u4e3b\u7ba1\u9053\u76f4\u5f84");
    model.param().set("L", "4*D", "\u4e3b\u7ba1\u9053\u957f\u5ea6");
    model.param().set("alpha", "45[deg]", "\u6362\u80fd\u5668\u7ba1\u9053\u503e\u89d2");
    model.param().set("D_transducer", "2[mm]", "\u6362\u80fd\u5668\u76f4\u5f84");
    model.param()
         .set("L_transducer", "D/cos(alpha)+3/2*D_transducer/cos(alpha)*tan(alpha)", "\u6362\u80fd\u5668\u7ba1\u9053\u957f\u5ea6");
    model.param().set("L2", "D/sin(alpha)", "\u4e3b\u6d41\u4e2d\u7684\u6362\u80fd\u5668\u7ba1\u9053\u957f\u5ea6");
    model.param()
         .set("L1", "0.5*(L_transducer-L2)", "\u652f\u6d41\u4e2d\u7684\u6362\u80fd\u5668\u7ba1\u9053\u957f\u5ea6");
    model.param().set("L_matching", "cp_match/f0/4", "\u5339\u914d\u5c42\u539a\u5ea6");
    model.param().set("L_piezo", "cp_pzt/f0/2", "\u538b\u7535\u6362\u80fd\u5668\u539a\u5ea6");
    model.param()
         .set("nLx", "cos(alpha)", "\u6362\u80fd\u5668\u7ba1\u9053\u65b9\u5411\u7684\u5355\u4f4d\u77e2\u91cf\uff08x \u5206\u91cf\uff09");
    model.param()
         .set("nLy", "0", "\u6362\u80fd\u5668\u7ba1\u9053\u65b9\u5411\u7684\u5355\u4f4d\u77e2\u91cf\uff08y \u5206\u91cf\uff09");
    model.param()
         .set("nLz", "sin(alpha)", "\u6362\u80fd\u5668\u7ba1\u9053\u65b9\u5411\u7684\u5355\u4f4d\u77e2\u91cf\uff08z \u5206\u91cf\uff09");
    model.param().label("\u51e0\u4f55\u53c2\u6570");
    model.param().create("par2");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2")
         .set("Uin", "10[m/s]", "\u5165\u53e3\u5904\u80cc\u666f\u5e73\u5747\u6d41\u7684\u5e73\u5747\u901f\u5ea6");
    model.param("par2").set("V0", "50[V]", "\u9a71\u52a8\u7535\u538b");
    model.param("par2").set("f0", "2.5[MHz]", "\u8f7d\u6ce2\u4fe1\u53f7\u4e2d\u5fc3\u9891\u7387");
    model.param("par2").set("T0", "1/f0", "\u8f7d\u6ce2\u4fe1\u53f7\u5468\u671f");
    model.param("par2").set("lam0", "cp_water/f0", "\u6c34\u4e2d\u7684\u4fe1\u53f7\u6ce2\u957f");
    model.param("par2").set("cp_water", "1481[m/s]", "\u58f0\u901f\uff0c\u6c34");
    model.param("par2").set("cp_pzt", "4620[m/s]", "\u538b\u529b\u6ce2\u901f\uff0cPZT");
    model.param("par2").set("cs_pzt", "1750[m/s]", "\u526a\u5207\u6ce2\u901f\uff0cPZT");
    model.param("par2").set("cp_match", "3400[m/s]", "\u538b\u529b\u6ce2\u901f\uff0c\u5339\u914d\u6750\u6599");
    model.param("par2").set("cs_match", "1920[m/s]", "\u526a\u5207\u6ce2\u901f\uff0c\u5339\u914d\u6750\u6599");
    model.param("par2").set("rho_match", "2280[kg/m^3]", "\u5bc6\u5ea6\uff0c\u5339\u914d\u6750\u6599");
    model.param("par2").set("cp_damp", "1500[m/s]", "\u538b\u529b\u6ce2\u901f\uff0c\u5438\u97f3\u6750\u6599");
    model.param("par2").set("cs_damp", "775[m/s]", "\u526a\u5207\u6ce2\u901f\uff0c\u5438\u97f3\u6750\u6599");
    model.param("par2").set("rho_damp", "6580[kg/m^3]", "\u5bc6\u5ea6\uff0c\u5438\u97f3\u6750\u6599");
    model.param("par2").label("\u6a21\u578b\u53c2\u6570");

    model.component("comp1").geom("geom1")
         .insertFile("flow_meter_piezoelectric_transducers_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("ige1");

    model.func().create("rect1", "Rectangle");
    model.func("rect1").set("lower", "0.5e-6");
    model.func("rect1").set("upper", "1.5e-6");
    model.func("rect1").set("smooth", "1e-6");
    model.func().create("an1", "Analytic");
    model.func("an1").set("funcname", "Vin");
    model.func("an1").set("expr", "V0*sin(2*pi*f0*t)*rect1(t)");
    model.func("an1").set("args", "t");
    model.func("an1").set("fununit", "V");
    model.func("an1").setIndex("argunit", "s", 0);
    model.func("an1").setIndex("plotargs", "10*T0", 0, 2);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").set(1, 2, 3);
    model.component("comp1").selection("sel1").label("\u6c34");
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").set(5, 9);
    model.component("comp1").selection("sel2").label("PZT");
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").set(6, 7);
    model.component("comp1").selection("sel3").label("\u5339\u914d\u5c42");
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").set(4, 8);
    model.component("comp1").selection("sel4").label("\u80cc\u886c");
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").geom(2);
    model.component("comp1").selection("sel5").set(2, 6, 16, 22, 30, 33, 40, 46, 55);
    model.component("comp1").selection("sel5").label("\u5bf9\u79f0");
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").geom(2);
    model.component("comp1").selection("sel6").set(1);
    model.component("comp1").selection("sel6").label("\u6d41\u4f53\u5165\u53e3");
    model.component("comp1").selection().create("sel7", "Explicit");
    model.component("comp1").selection("sel7").geom(2);
    model.component("comp1").selection("sel7").set(19);
    model.component("comp1").selection("sel7").label("\u6d41\u4f53\u51fa\u53e3");
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u56fa\u4f53");
    model.component("comp1").selection("uni1").set("input", new String[]{"sel2", "sel3", "sel4"});

    model.component("comp1").coordSystem().create("sys2", "VectorBase");
    model.component("comp1").coordSystem("sys2").setIndex("base", "cos(alpha)", 0, 0);
    model.component("comp1").coordSystem("sys2").setIndex("base", "-sin(alpha)", 0, 2);
    model.component("comp1").coordSystem("sys2").setIndex("base", "sin(alpha)", 2, 0);
    model.component("comp1").coordSystem("sys2").setIndex("base", "cos(alpha)", 2, 2);
    model.component("comp1").coordSystem("sys2").set("orthonormal", true);
    model.component("comp1").coordSystem("sys2").label("\u6362\u80fd\u5668\u5750\u6807\u7cfb");

    model.component("comp1").physics().create("spf", "TurbulentFlowkomega", "geom1");
    model.component("comp1").physics("spf").selection().set();
    model.component("comp1").physics("spf").selection().named("sel1");
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").selection().named("sel6");
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("Uavfdf", "Uin");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").selection().named("sel7");
    model.component("comp1").physics("spf").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("spf").feature("sym1").selection().named("sel5");
    model.component("comp1").physics("spf").feature("dcont1").set("pairDisconnect", true);
    model.component("comp1").physics().create("cwe", "ConvectedWaveEquation", "geom1");
    model.component("comp1").physics("cwe").selection().set();
    model.component("comp1").physics("cwe").selection().named("sel1");
    model.component("comp1").physics("cwe").create("imp1", "AcousticImpedance", 2);
    model.component("comp1").physics("cwe").feature("imp1").selection().set(1, 19);
    model.component("comp1").physics("cwe").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("cwe").feature("sym1").selection().named("sel5");
    model.component("comp1").physics().create("elte", "ElasticWavesTimeExplicit", "geom1");
    model.component("comp1").physics("elte").selection().set();
    model.component("comp1").physics("elte").selection().named("uni1");
    model.component("comp1").physics("elte").create("pzm1", "PiezoelectricMaterialModel", 3);
    model.component("comp1").physics("elte").feature("eltem1").set("IsotropicOption", "CpCs");
    model.component("comp1").physics("elte").feature("pzm1").selection().named("sel2");
    model.component("comp1").physics("elte").feature("pzm1").set("coordinateSystem", "sys2");
    model.component("comp1").physics("elte").create("lrb1", "LowReflectingBoundary", 2);
    model.component("comp1").physics("elte").feature("lrb1").selection().set(21, 52);
    model.component("comp1").physics("elte").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("elte").feature("sym1").selection().named("sel5");
    model.component("comp1").physics("elte").feature("eltem1").create("dmp1", "Damping", 3);
    model.component("comp1").physics("elte").feature("eltem1").feature("dmp1").selection().named("sel3");
    model.component("comp1").physics("elte").feature("eltem1").feature("dmp1")
         .set("InputParameters", "DampingRatios");
    model.component("comp1").physics("elte").feature("eltem1").feature("dmp1").set("f1", "0.99*f0");
    model.component("comp1").physics("elte").feature("eltem1").feature("dmp1").set("zeta1", 0.01);
    model.component("comp1").physics("elte").feature("eltem1").feature("dmp1").set("f2", "1.01*f0");
    model.component("comp1").physics("elte").feature("eltem1").feature("dmp1").set("zeta2", 0.01);
    model.component("comp1").physics("elte").feature("eltem1").create("dmp2", "Damping", 3);
    model.component("comp1").physics("elte").feature("eltem1").feature("dmp2").selection().named("sel4");
    model.component("comp1").physics("elte").feature("eltem1").feature("dmp2")
         .set("InputParameters", "DampingRatios");
    model.component("comp1").physics("elte").feature("eltem1").feature("dmp2").set("f1", "0.99*f0");
    model.component("comp1").physics("elte").feature("eltem1").feature("dmp2").set("zeta1", 0.025);
    model.component("comp1").physics("elte").feature("eltem1").feature("dmp2").set("f2", "1.01*f0");
    model.component("comp1").physics("elte").feature("eltem1").feature("dmp2").set("zeta2", 0.025);
    model.component("comp1").physics("elte").feature("pzm1").create("mdmp1", "MechanicalDamping", 3);
    model.component("comp1").physics("elte").feature("pzm1").feature("mdmp1")
         .set("InputParameters", "DampingRatios");
    model.component("comp1").physics("elte").feature("pzm1").feature("mdmp1").set("f1", "0.99*f0");
    model.component("comp1").physics("elte").feature("pzm1").feature("mdmp1").set("zeta1", 0.005);
    model.component("comp1").physics("elte").feature("pzm1").feature("mdmp1").set("f2", "1.01*f0");
    model.component("comp1").physics("elte").feature("pzm1").feature("mdmp1").set("zeta2", 0.005);
    model.component("comp1").physics().create("es", "Electrostatics", "geom1");
    model.component("comp1").physics("es").selection().set();
    model.component("comp1").physics("es").selection().named("sel2");
    model.component("comp1").physics("es").create("ccnp1", "ChargeConservationPiezo", 3);
    model.component("comp1").physics("es").feature("ccnp1").selection().named("sel2");
    model.component("comp1").physics("es").create("gnd1", "Ground", 2);
    model.component("comp1").physics("es").feature("gnd1").selection().set(29, 57);
    model.component("comp1").physics("es").create("pot1", "ElectricPotential", 2);
    model.component("comp1").physics("es").feature("pot1").selection().set(32);
    model.component("comp1").physics("es").feature("pot1").set("V0", "Vin(t)");
    model.component("comp1").physics("es").create("fp1", "FloatingPotential", 2);
    model.component("comp1").physics("es").feature("fp1").selection().set(54);
    model.component("comp1").physics("es").feature("fp1").set("Group", true);
    model.component("comp1").physics("es").create("symp1", "SymmetryPlane", 2);
    model.component("comp1").physics("es").feature("symp1").selection().named("sel5");

    model.component("comp1").multiphysics().create("pzete1", "PiezoelectricEffectTimeExplicit", 3);
    model.component("comp1").multiphysics()
         .create("cspte1", "PairConvectedAcousticStructureBoundaryTimeExplicit", 2);
    model.component("comp1").multiphysics("cspte1").set("pairs", new String[]{"ap1", "ap2"});
    model.component("comp1").multiphysics().create("bffc1", "BackgroundFluidFlowCoupling", 3);
    model.component("comp1").multiphysics("bffc1").selection().named("sel1");

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
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("StrainCharge", "StrainCharge", "Strain-charge form");
    model.component("comp1").material("mat2").propertyGroup()
         .create("StressCharge", "StressCharge", "Stress-charge form");
    model.component("comp1").material("mat2").label("Lead Zirconate Titanate (PZT-5H)");
    model.component("comp1").material("mat2").set("family", "lead");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "440[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.3[W/(m*K)]", "0", "0", "0", "1.3[W/(m*K)]", "0", "0", "0", "1.3[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1704.4", "0", "0", "0", "1704.4", "0", "0", "0", "1433.6"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "7500[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("StrainCharge").label("Strain-charge form");
    model.component("comp1").material("mat2").propertyGroup("StrainCharge")
         .set("sE", new String[]{"1.65e-011[1/Pa]", "-4.78e-012[1/Pa]", "-8.45e-012[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "-4.78e-012[1/Pa]", "1.65e-011[1/Pa]", "-8.45e-012[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "0[1/Pa]", "-8.45e-012[1/Pa]", "-8.45e-012[1/Pa]", "2.07e-011[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "4.35e-011[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "4.35e-011[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "4.26e-011[1/Pa]"});
    model.component("comp1").material("mat2").propertyGroup("StrainCharge")
         .set("dET", new String[]{"0[C/N]", "0[C/N]", "-2.74e-010[C/N]", "0[C/N]", "0[C/N]", "-2.74e-010[C/N]", "0[C/N]", "0[C/N]", "5.93e-010[C/N]", "0[C/N]", 
         "7.41e-010[C/N]", "0[C/N]", "7.41e-010[C/N]", "0[C/N]", "0[C/N]", "0[C/N]", "0[C/N]", "0[C/N]"});
    model.component("comp1").material("mat2").propertyGroup("StrainCharge")
         .set("epsilonrT", new String[]{"3130", "0", "0", "0", "3130", "0", "0", "0", "3400"});
    model.component("comp1").material("mat2").propertyGroup("StressCharge").label("Stress-charge form");
    model.component("comp1").material("mat2").propertyGroup("StressCharge")
         .set("cE", new String[]{"1.27205e+011[Pa]", "8.02122e+010[Pa]", "8.46702e+010[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "8.02122e+010[Pa]", "1.27205e+011[Pa]", "8.46702e+010[Pa]", "0[Pa]", 
         "0[Pa]", "0[Pa]", "8.46702e+010[Pa]", "8.46702e+010[Pa]", "1.17436e+011[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", 
         "0[Pa]", "2.29885e+010[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "2.29885e+010[Pa]", "0[Pa]", 
         "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "2.34742e+010[Pa]"});
    model.component("comp1").material("mat2").propertyGroup("StressCharge")
         .set("eES", new String[]{"0[C/m^2]", "0[C/m^2]", "-6.62281[C/m^2]", "0[C/m^2]", "0[C/m^2]", "-6.62281[C/m^2]", "0[C/m^2]", "0[C/m^2]", "23.2403[C/m^2]", "0[C/m^2]", 
         "17.0345[C/m^2]", "0[C/m^2]", "17.0345[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]"});
    model.component("comp1").material("mat2").propertyGroup("StressCharge")
         .set("epsilonrS", new String[]{"1704.4", "0", "0", "0", "1704.4", "0", "0", "0", "1433.6"});
    model.component("comp1").material("mat2").selection().named("sel2");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").selection().named("sel3");
    model.component("comp1").material("mat3").label("\u5339\u914d\u5c42\u6750\u6599");
    model.component("comp1").material("mat3").propertyGroup()
         .create("CpCs", "CpCs", "Pressure_wave_and_shear_wave_speeds");
    model.component("comp1").material("mat3").propertyGroup("CpCs").set("cp", new String[]{"cp_match"});
    model.component("comp1").material("mat3").propertyGroup("CpCs").set("cs", new String[]{"cs_match"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", new String[]{"rho_match"});
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").selection().named("sel4");
    model.component("comp1").material("mat4").label("\u5438\u97f3\u6750\u6599");
    model.component("comp1").material("mat4").propertyGroup()
         .create("CpCs", "CpCs", "Pressure_wave_and_shear_wave_speeds");
    model.component("comp1").material("mat4").propertyGroup("CpCs").set("cp", new String[]{"cp_damp"});
    model.component("comp1").material("mat4").propertyGroup("CpCs").set("cs", new String[]{"cs_damp"});
    model.component("comp1").material("mat4").propertyGroup("def").set("density", new String[]{"rho_damp"});

    model.component("comp1").mesh("mesh1").contribute("physics/cwe", false);
    model.component("comp1").mesh("mesh1").contribute("physics/elte", false);
    model.component("comp1").mesh("mesh1").contribute("physics/es", false);
    model.component("comp1").mesh("mesh1").contribute("multiphysics/pzete1", false);
    model.component("comp1").mesh("mesh1").contribute("multiphysics/cspte1", false);
    model.component("comp1").mesh("mesh1").contribute("multiphysics/bffc1", false);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").label("\u7f51\u683c 1 - CFD");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/cwe", false);
    model.study("std1").feature("stat").setSolveFor("/physics/elte", false);
    model.study("std1").feature("stat").setSolveFor("/physics/es", false);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/pzete1", false);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/cspte1", false);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/bffc1", false);
    model.study("std1").setGenPlots(false);
    model.study("std1").label("\u7814\u7a76 1 - CFD");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.component("comp1").sorder("quadratic");

    model.component("comp1").coordSystem().create("ab1", "AbsorbingLayer");
    model.component("comp1").coordSystem("ab1").selection().set(1, 3);

    model.component("comp1").mesh().create("mesh2");
    model.component("comp1").mesh("mesh2").contribute("geom/detail", true);
    model.component("comp1").mesh("mesh2").label("\u7f51\u683c 2 - \u58f0\u5b66");
    model.component("comp1").mesh("mesh2").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh2").feature("ftri1").selection().set(32, 35, 37, 42, 43, 54);
    model.component("comp1").mesh("mesh2").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh2").feature("ftri1").feature("size1").selection().set(32, 54);
    model.component("comp1").mesh("mesh2").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh2").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh2").feature("ftri1").feature("size1").set("hmax", "cs_pzt/f0/2");
    model.component("comp1").mesh("mesh2").feature("ftri1").create("size2", "Size");
    model.component("comp1").mesh("mesh2").feature("ftri1").feature("size2").selection().set(35, 37, 42, 43);
    model.component("comp1").mesh("mesh2").feature("ftri1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh2").feature("ftri1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh2").feature("ftri1").feature("size2").set("hmax", "cs_match/f0/2");
    model.component("comp1").mesh("mesh2").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh2").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh2").feature("swe1").selection().named("sel2");
    model.component("comp1").mesh("mesh2").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh2").feature("swe1").feature("dis1").set("numelem", 4);
    model.component("comp1").mesh("mesh2").create("swe2", "Sweep");
    model.component("comp1").mesh("mesh2").feature("swe2").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh2").feature("swe2").selection().named("sel3");
    model.component("comp1").mesh("mesh2").feature("swe2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh2").feature("swe2").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh2").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh2").feature("ftet1").set("optlevel", "high");
    model.component("comp1").mesh("mesh2").feature("ftet1").set("optsmall", true);
    model.component("comp1").mesh("mesh2").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh2").feature("ftet1").feature("size1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh2").feature("ftet1").feature("size1").selection().named("sel1");
    model.component("comp1").mesh("mesh2").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh2").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh2").feature("ftet1").feature("size1").set("hmax", "lam0/1.5");
    model.component("comp1").mesh("mesh2").feature("ftet1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh2").feature("ftet1").feature("size1").set("hmin", "lam0/3");
    model.component("comp1").mesh("mesh2").feature("ftet1").create("size2", "Size");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").mesh("mesh2").feature("ftet1").feature("size2").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh2").feature("ftet1").feature("size2").selection().named("sel4");
    model.component("comp1").mesh("mesh2").feature("ftet1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh2").feature("ftet1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh2").feature("ftet1").feature("size2").set("hmax", "cs_damp/f0/1.5");
    model.component("comp1").mesh("mesh2").run();

    model.study().create("std2");
    model.study("std2").create("mapp", "Mapping");
    model.study("std2").feature("mapp").set("solnum", "auto");
    model.study("std2").feature("mapp").set("notsolnum", "auto");
    model.study("std2").feature("mapp").set("outputmap", new String[]{});
    model.study("std2").feature("mapp").setSolveFor("/physics/spf", false);
    model.study("std2").feature("mapp").setSolveFor("/physics/cwe", false);
    model.study("std2").feature("mapp").setSolveFor("/physics/elte", false);
    model.study("std2").feature("mapp").setSolveFor("/physics/es", false);
    model.study("std2").feature("mapp").setSolveFor("/multiphysics/pzete1", false);
    model.study("std2").feature("mapp").setSolveFor("/multiphysics/cspte1", false);
    model.study("std2").feature("mapp").setSolveFor("/multiphysics/bffc1", true);
    model.study("std2").feature("mapp").setSolveFor("/physics/spf", false);
    model.study("std2").feature("mapp").setSolveFor("/physics/cwe", false);
    model.study("std2").label("\u7814\u7a76 2 - \u6620\u5c04");
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("mapp").set("notstudy", "std1");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.study().create("std3");
    model.study("std3").create("time", "Transient");
    model.study("std3").feature("time").setSolveFor("/physics/spf", false);
    model.study("std3").feature("time").setSolveFor("/physics/cwe", true);
    model.study("std3").feature("time").setSolveFor("/physics/elte", true);
    model.study("std3").feature("time").setSolveFor("/physics/es", true);
    model.study("std3").feature("time").setSolveFor("/multiphysics/pzete1", true);
    model.study("std3").feature("time").setSolveFor("/multiphysics/cspte1", true);
    model.study("std3").feature("time").setSolveFor("/multiphysics/bffc1", false);
    model.study("std3").setGenPlots(false);
    model.study("std3").label("\u7814\u7a76 3 - \u58f0\u5b66");
    model.study("std3").feature("time").set("tlist", "range(0, T0/5, 30*T0)");
    model.study("std3").feature("time").set("usesol", true);
    model.study("std3").feature("time").set("notsolmethod", "sol");
    model.study("std3").feature("time").set("notstudy", "std2");
    model.study("std3").feature("time").setEntry("outputmap", "spf", "selection");
    model.study("std3").feature("time").setEntry("outputselectionmap", "spf", "sel5");
    model.study("std3").feature("time").setEntry("outputmap", "cwe", "selection");
    model.study("std3").feature("time").setEntry("outputselectionmap", "cwe", "sel5");
    model.study("std3").feature("time").setEntry("outputmap", "elte", "selection");
    model.study("std3").feature("time").setEntry("outputselectionmap", "elte", "sel5");
    model.study("std3").feature("time").setEntry("outputmap", "es", "selection");
    model.study("std3").feature("time").setEntry("outputselectionmap", "es", "sel5");
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u80cc\u666f\u6d41\u901f");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("\u58f0\u538b\u548c\u5f39\u6027\u538b\u529b");
    model.result("pg2").set("titletype", "label");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "p2");
    model.result("pg2").feature("surf1").set("colortable", "Wave");
    model.result("pg2").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg2").feature("surf1").set("resolution", "custom");
    model.result("pg2").feature("surf1").set("refine", 6);
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection().named("sel5");
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("surf2", "surf1");
    model.result("pg2").run();
    model.result("pg2").feature("surf2").set("expr", "elte.p");
    model.result("pg2").feature("surf2").set("descr", "\u538b\u529b");
    model.result("pg2").feature("surf2").set("inheritplot", "surf1");
    model.result("pg2").create("surf3", "Surface");
    model.result("pg2").feature("surf3").set("expr", "1");
    model.result("pg2").feature("surf3").create("sel1", "Selection");
    model.result("pg2").feature("surf3").feature("sel1").selection().set(3, 4, 7, 8, 9, 11, 12, 13, 17, 18);
    model.result("pg2").run();
    model.result("pg2").feature("surf3").create("mtrl1", "MaterialAppearance");
    model.result("pg2").run();
    model.result("pg2").feature("surf3").feature("mtrl1").set("appearance", "custom");
    model.result("pg2").feature("surf3").feature("mtrl1").set("family", "steel");
    model.result("pg2").create("surf4", "Surface");
    model.result("pg2").feature("surf4").set("expr", "1");
    model.result("pg2").feature("surf4").create("sel1", "Selection");
    model.result("pg2").feature("surf4").feature("sel1").selection().set(20, 23, 34, 38, 39, 44, 47, 50);
    model.result("pg2").run();
    model.result("pg2").feature("surf4").create("mtrl1", "MaterialAppearance");
    model.result("pg2").run();
    model.result("pg2").feature("surf4").feature("mtrl1").set("appearance", "custom");
    model.result("pg2").feature("surf4").feature("mtrl1").set("color", "custom");
    model.result("pg2").feature("surf4").feature("mtrl1")
         .set("customcolor", new double[]{1, 0.6274510025978088, 0.47843137383461});
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 51, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 76, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 101, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 126, 0);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u53d1\u5c04\u548c\u63a5\u6536\u7684\u4fe1\u53f7");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").setIndex("looplevelinput", "interp", 0);
    model.result("pg3").setIndex("interp", "range(0, T0/20, 30*T0)", 0);
    model.result("pg3").set("titletype", "label");
    model.result("pg3").create("ptgr1", "PointGraph");
    model.result("pg3").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg3").feature("ptgr1").set("linewidth", "preference");
    model.result("pg3").feature("ptgr1").selection().set(39);
    model.result("pg3").feature("ptgr1").set("expr", "V/V0");
    model.result("pg3").feature("ptgr1").set("legend", true);
    model.result("pg3").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg3").feature("ptgr1").setIndex("legends", "\u76f8\u5bf9\u9a71\u52a8\u7535\u538b", 0);
    model.result("pg3").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg3").run();
    model.result("pg3").feature("ptgr2").selection().set(73);
    model.result("pg3").feature("ptgr2").setIndex("legends", "\u76f8\u5bf9\u63a5\u6536\u7535\u538b", 0);
    model.result("pg3").run();
    model.result("pg3").set("twoyaxes", true);
    model.result("pg3").setIndex("plotonsecyaxis", true, 1, 1);
    model.result("pg3").set("axislimits", true);
    model.result("pg3").set("xmin", 0);
    model.result("pg3").set("xmax", "30*T0");
    model.result("pg3").set("ymin", -1);
    model.result("pg3").set("ymax", 1);
    model.result("pg3").set("yminsec", -0.015);
    model.result("pg3").set("ymaxsec", 0.015);
    model.result("pg3").set("legendpos", "uppermiddle");
    model.result("pg3").run();

    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledcoordinatesystems", new String[]{"ab1"});

    model.result("pg2").run();

    model.title("\u5e26\u538b\u7535\u6362\u80fd\u5668\u7684\u8d85\u58f0\u6ce2\u6d41\u91cf\u8ba1");

    model
         .description("\u8d85\u58f0\u6ce2\u6d41\u91cf\u8ba1\u901a\u8fc7\u5728\u7ba1\u9053\u4e2d\u659c\u5411\u53d1\u9001\u8d85\u58f0\u6ce2\u4fe1\u53f7\u4f7f\u5176\u7a7f\u8fc7\u6d41\u4f53\uff0c\u4ee5\u6d4b\u5b9a\u6d41\u4f53\u6d41\u7ecf\u7ba1\u9053\u7684\u901f\u5ea6\u3002\u5728\u6ca1\u6709\u6d41\u52a8\u7684\u60c5\u51b5\u4e0b\uff0c\u4e0a\u6e38\u548c\u4e0b\u6e38\u65b9\u5411\u7684\u4fe1\u53f7\u5728\u53d1\u5c04\u5668\u4e0e\u63a5\u6536\u5668\u4e4b\u95f4\u7684\u4f20\u8f93\u65f6\u95f4\u662f\u76f8\u540c\u7684\uff1b\u4f46\u5b58\u5728\u6d41\u52a8\u65f6\uff0c\u5411\u4e0b\u6e38\u4f20\u64ad\u7684\u6ce2\u6bd4\u5411\u4e0a\u6e38\u4f20\u64ad\u7684\u6ce2\u79fb\u52a8\u5f97\u66f4\u5feb\uff0c\u8fd9\u53ef\u4ee5\u7528\u6765\u786e\u5b9a\u6d41\u52a8\u72b6\u51b5\u3002\u5728\u8bb8\u591a\u60c5\u51b5\u4e0b\uff0c\u4eba\u4eec\u5e38\u4f7f\u7528\u538b\u7535\u6362\u80fd\u5668\u6765\u53d1\u9001\u548c\u63a5\u6536\u8d85\u58f0\u6ce2\u3002\n\n\u672c\u6559\u7a0b\u6f14\u793a\u5982\u4f55\u5728\u5b58\u5728\u6d41\u52a8\u7684\u60c5\u51b5\u4e0b\u6a21\u62df\u5e26\u538b\u7535\u6362\u80fd\u5668\u7684\u8d85\u58f0\u6ce2\u6d41\u91cf\u8ba1\u3002\u8be5\u6a21\u578b\u57fa\u4e8e\u975e\u5e38\u9002\u7528\u4e8e\u5927\u578b\u58f0\u5b66\u77ac\u6001\u95ee\u9898\u7684\u95f4\u65ad\u4f3d\u8fbd\u91d1 (dG) \u65b9\u6cd5\uff0c\u4f7f\u7528\u201c\u5f39\u6027\u6ce2\uff0c\u65f6\u57df\u663e\u5f0f\u201d\u548c\u201c\u5bf9\u6d41\u6ce2\u52a8\u65b9\u7a0b\uff0c\u65f6\u57df\u663e\u5f0f\u201d\u7269\u7406\u573a\u63a5\u53e3\u5206\u522b\u6a21\u62df\u6ce2\u5728\u56fa\u4f53\u57df\u548c\u6d41\u4f53\u57df\u4e2d\u7684\u4f20\u64ad\uff0c\u4e24\u8005\u901a\u8fc7\u201c\u5bf9\uff0c\u5bf9\u6d41\u58f0-\u7ed3\u6784\u8fb9\u754c\uff0c\u65f6\u57df\u663e\u5f0f\u201d\u591a\u7269\u7406\u573a\u7279\u5f81\u8fde\u63a5\u3002\u672c\u4f8b\u4f7f\u7528\u201c\u538b\u7535\uff0c\u65f6\u57df\u663e\u5f0f\u201d\u591a\u7269\u7406\u573a\u7279\u5f81\uff08\u5176\u4e2d\u8026\u5408\u4e86\u201c\u5f39\u6027\u6ce2\uff0c\u65f6\u57df\u663e\u5f0f\u201d\u4e0e\u201c\u9759\u7535\u201d\u7269\u7406\u573a\u63a5\u53e3\uff09\u5bf9\u538b\u7535\u6362\u80fd\u5668\u8fdb\u884c\u5efa\u6a21\uff0c\u5176\u4e2d\u8fd8\u5229\u7528\u4e86\u51e0\u4f55\u88c5\u914d\u548c\u975e\u5171\u5f62\u7f51\u683c\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("flow_meter_piezoelectric_transducers.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
