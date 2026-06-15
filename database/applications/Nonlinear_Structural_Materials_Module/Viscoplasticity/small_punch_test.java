/*
 * small_punch_test.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:39 by COMSOL 6.3.0.290. */
public class small_punch_test {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Nonlinear_Structural_Materials_Module\\Viscoplasticity");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/solid", true);

    model.param().label("\u51e0\u4f55\u53c2\u6570");
    model.param().set("D", "6.4[mm]");
    model.param().descr("D", "\u8bd5\u6837\u76f4\u5f84");
    model.param().set("th", "0.5[mm]");
    model.param().descr("th", "\u8bd5\u6837\u539a\u5ea6");
    model.param().set("r_punch", "1.27[mm]");
    model.param().descr("r_punch", "\u51b2\u5934\u534a\u5f84");
    model.param().set("rout_clamp", "3.22[mm]");
    model.param().descr("rout_clamp", "\u5939\u5177\u5916\u534a\u5f84");
    model.param().set("r_bottom", "1.3[mm]");
    model.param().descr("r_bottom", "\u5939\u5177\u5e95\u534a\u5f84");
    model.param().set("r_top", "1.94[mm]");
    model.param().descr("r_top", "\u5939\u5177\u9876\u534a\u5f84");
    model.param().set("h_punch", "3.27[mm]");
    model.param().descr("h_punch", "\u51b2\u5934\u9ad8\u5ea6");
    model.param().set("r_fillet", "0.25[mm]");
    model.param().descr("r_fillet", "\u5706\u89d2\u534a\u5f84");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"D/2", "th"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"rout_clamp-r_bottom", "1"});
    model.component("comp1").geom("geom1").feature("r2").setIndex("size", "th/4", 1);
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"r_bottom", "-th/4"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"rout_clamp-r_top", "1"});
    model.component("comp1").geom("geom1").feature("r3").setIndex("size", "th", 1);
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"r_top", "th"});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "r_punch");
    model.component("comp1").geom("geom1").feature("c1").set("angle", 90);
    model.component("comp1").geom("geom1").feature("c1").set("pos", new String[]{"0", "-r_punch"});
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").set("size", new String[]{"r_punch", "h_punch-r_punch"});
    model.component("comp1").geom("geom1").feature("r4").set("pos", new String[]{"0", "-h_punch"});
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").selection("pointinsketch").set("r3", 1);
    model.component("comp1").geom("geom1").feature("fil1").set("radius", "r_fillet");
    model.component("comp1").geom("geom1").run("fil1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("c1", "r4");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("pairtype", "contact");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("mce1", "MeshControlEdges");
    model.component("comp1").geom("geom1").feature("mce1").selection("input").set("fin", 4);
    model.component("comp1").geom("geom1").run("mce1");

    model.component("comp1").pair("ap1").swap();
    model.component("comp1").pair("ap2").swap();
    model.component("comp1").pair("ap2").manualSelection(true);
    model.component("comp1").pair("ap2").source().set(12, 14, 16);
    model.component("comp1").pair().create("p3", "Contact");
    model.component("comp1").pair("p3").source().set(3);
    model.component("comp1").pair("p3").destination().set(5);

    model.param().create("par2");
    model.param("par2").label("\u6750\u6599\u53c2\u6570");
    model.param("par2").set("mu_initial", "10[MPa]");
    model.param("par2").descr("mu_initial", "\u521d\u59cb\u526a\u5207\u6a21\u91cf");
    model.param("par2").set("mu", "9.4[MPa]");
    model.param("par2").descr("mu", "\u5b8f\u89c2\u526a\u5207\u6a21\u91cf");
    model.param("par2").set("betav1", "20");
    model.param("par2").descr("betav1", "\u80fd\u91cf\u56e0\u5b50\uff0c\u5206\u652f 1");
    model.param("par2").set("betav2_i", "29.3");
    model.param("par2").descr("betav2_i", "\u521d\u59cb\u80fd\u91cf\u56e0\u5b50\uff0c\u5206\u652f 2");
    model.param("par2").set("betav2_f", "7.91");
    model.param("par2").descr("betav2_f", "\u6700\u7ec8\u80fd\u91cf\u56e0\u5b50\uff0c\u5206\u652f 2");
    model.param("par2").set("q", "0.23");
    model.param("par2").descr("q", "\u5e94\u53d8\u80fd\u4e2d I2 \u7684\u76f8\u5bf9\u8d21\u732e");
    model.param("par2").set("lambdalock", "3.25");
    model.param("par2").descr("lambdalock", "\u9501\u5b9a\u62c9\u4f38");
    model.param("par2").set("N", "lambdalock^2");
    model.param("par2").descr("N", "\u6bb5\u6570");
    model.param("par2").set("Kappa", "6[GPa]");
    model.param("par2").descr("Kappa", "\u4f53\u79ef\u6a21\u91cf");
    model.param("par2").set("sig1res", "3.25[MPa]*sqrt(3/2)");
    model.param("par2").descr("sig1res", "\u6d41\u963b\uff0c\u5206\u652f 1");
    model.param("par2").set("sig2res", "20.1[MPa]*sqrt(3/2)");
    model.param("par2").descr("sig2res", "\u6d41\u963b\uff0c\u5206\u652f 2");
    model.param("par2").set("a", "0.073*sqrt(3/2)");
    model.param("par2").descr("a", "\u8026\u5408\u538b\u529b\u7cfb\u6570");
    model.param("par2").set("n", "20");
    model.param("par2").descr("n", "\u5e94\u529b\u6307\u6570");
    model.param("par2").set("alfa_rate", "31.9");
    model.param("par2").descr("alfa_rate", "\u526a\u5207\u6a21\u91cf\u7684\u6f14\u53d8\u901f\u7387\u7cfb\u6570");

    model.component("comp1").physics("solid").create("hmm1", "HyperelasticModel", 2);
    model.component("comp1").physics("solid").feature("hmm1").selection().set(2);
    model.component("comp1").physics("solid").feature("hmm1").set("MaterialModel", "ArrudaBoyce");
    model.component("comp1").physics("solid").feature("hmm1")
         .set("Compressibility_ArrudaBoyce", "CompressibleUncoupled");
    model.component("comp1").physics("solid").feature("hmm1").set("reducedIntegration", true);
    model.component("comp1").physics("solid").feature("hmm1").set("CalculateDissipatedEnergy", true);
    model.component("comp1").physics("solid").feature("hmm1").create("pvp1", "PolymerViscoplasticity", 2);
    model.component("comp1").physics("solid").feature("hmm1").feature("pvp1")
         .set("ViscoplasticityModel", "BergstromBischoff");
    model.component("comp1").physics("solid").feature("hmm1").feature("pvp1").set("betav1", "betav1/(1/(1+q))");
    model.component("comp1").physics("solid").feature("hmm1").feature("pvp1").set("betav2_i", "betav2_i/(1/(1+q))");
    model.component("comp1").physics("solid").feature("hmm1").feature("pvp1").set("betav2_f", "betav2_f/(1/(1+q))");
    model.component("comp1").physics("solid").feature("hmm1").feature("pvp1").set("alpha", "alfa_rate");
    model.component("comp1").physics("solid").feature("hmm1").feature("pvp1").set("timeMethod", "ode");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").selection().set(2);
    model.component("comp1").material("mat1").label("UHMWPE");
    model.component("comp1").material("mat1").propertyGroup().create("KG", "KG", "Bulk_modulus_and_shear_modulus");
    model.component("comp1").material("mat1").propertyGroup("KG").set("K", new String[]{"Kappa*(1/(1+q))+2*Kappa"});
    model.component("comp1").material("mat1").propertyGroup().create("ArrudaBoyce", "ArrudaBoyce", "Arruda-Boyce");
    model.component("comp1").material("mat1").propertyGroup("ArrudaBoyce").set("Nseg", new String[]{"N"});
    model.component("comp1").material("mat1").propertyGroup("ArrudaBoyce").set("mu0", new String[]{"mu*(1/(1+q))"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"0"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("BergstromBischoff", "BergstromBischoff", "Bergstrom-Bischoff_viscoplasticity");
    model.component("comp1").material("mat1").propertyGroup("BergstromBischoff")
         .set("A_BeBi", new String[]{"1[1/s]*sqrt(2/3)"});
    model.component("comp1").material("mat1").propertyGroup("BergstromBischoff")
         .set("sigRes1_BeBi", new String[]{"sig1res"});
    model.component("comp1").material("mat1").propertyGroup("BergstromBischoff")
         .set("n1_BeBi", new String[]{"if(t<1[s],1,n)"});
    model.component("comp1").material("mat1").propertyGroup("BergstromBischoff")
         .set("a1_BeBi", new String[]{"a/((1/(1+q))+2)"});
    model.component("comp1").material("mat1").propertyGroup("BergstromBischoff")
         .set("sigRes2_BeBi", new String[]{"sig2res"});
    model.component("comp1").material("mat1").propertyGroup("BergstromBischoff")
         .set("n2_BeBi", new String[]{"if(t<1[s],1,n)"});
    model.component("comp1").material("mat1").propertyGroup("BergstromBischoff")
         .set("a2_BeBi", new String[]{"1/((1/(1+q))+2)"});

    model.component("comp1").physics("solid").feature("hmm1").create("exs1", "ExternalStress", 2);
    model.component("comp1").physics("solid").feature("hmm1").feature("exs1")
         .set("Sext", new String[]{"q/(1+q)*mu_initial*solid.J^(-2/3)*solid.I1CIel", "0", "0", "0", "q/(1+q)*mu_initial*solid.J^(-2/3)*solid.I1CIel", "0", "0", "0", "q/(1+q)*mu_initial*solid.J^(-2/3)*solid.I1CIel"});
    model.component("comp1").physics("solid").feature("hmm1").create("exs2", "ExternalStress", 2);
    model.component("comp1").physics("solid").feature("hmm1").feature("exs2")
         .set("StressInputType", "StressTensorSpatial");
    model.component("comp1").physics("solid").feature("hmm1").feature("exs2")
         .set("sext", new String[]{"-(2/3)*q/(1+q)*mu_initial*solid.I2CIel/solid.J", "0", "0", "0", "-(2/3)*q/(1+q)*mu_initial*solid.I2CIel/solid.J", "0", "0", "0", "-(2/3)*q/(1+q)*mu_initial*solid.I2CIel/solid.J"});
    model.component("comp1").physics("solid").feature("hmm1").create("exs3", "ExternalStress", 2);
    model.component("comp1").physics("solid").feature("hmm1").feature("exs3")
         .set("Sext", new String[]{"-(q/(1+q))*mu_initial*solid.J^(-4/3)*solid.Cel11", "-(q/(1+q))*mu_initial*solid.J^(-4/3)*solid.Cel12", "-(q/(1+q))*mu_initial*solid.J^(-4/3)*solid.Cel13", "-(q/(1+q))*mu_initial*solid.J^(-4/3)*solid.Cel12", "-(q/(1+q))*mu_initial*solid.J^(-4/3)*solid.Cel22", "-(q/(1+q))*mu_initial*solid.J^(-4/3)*solid.Cel23", "-(q/(1+q))*mu_initial*solid.J^(-4/3)*solid.Cel13", "-(q/(1+q))*mu_initial*solid.J^(-4/3)*solid.Cel23", "-(q/(1+q))*mu_initial*solid.J^(-4/3)*solid.Cel33"});

    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").selection().set(1, 3, 4);
    model.component("comp1").material("mat2").label("\u94a2");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", new String[]{"210[GPa]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"7800[kg/m^3]"});

    model.component("comp1").physics("solid").create("rd1", "RigidDomain", 2);
    model.component("comp1").physics("solid").feature("rd1").label("\u521a\u6027\u6750\u6599\uff1a\u5939\u5177");
    model.component("comp1").physics("solid").feature("rd1").selection().set(3, 4);
    model.component("comp1").physics("solid").feature("rd1").create("fix1", "FixedConstraint", -1);
    model.component("comp1").physics("solid").create("rd2", "RigidDomain", 2);
    model.component("comp1").physics("solid").feature("rd2").selection().set(1);
    model.component("comp1").physics("solid").feature("rd2").label("\u521a\u6027\u6750\u6599\uff1a\u51b2\u5934");

    model.param().create("par3");
    model.param("par3").label("\u8bd5\u9a8c\u53c2\u6570");
    model.param("par3").set("wdot", "0.5[mm/min]");
    model.param("par3").descr("wdot", "\u51b2\u5934\u901f\u5ea6");
    model.param("par3").set("wmax", "4[mm]");
    model.param("par3").descr("wmax", "\u6700\u5927\u4f4d\u79fb");
    model.param("par3").set("Dt", "wmax/wdot");
    model.param("par3").descr("Dt", "\u8fd0\u52a8\u65f6\u95f4");
    model.param("par3").set("Dt_transient", "12[s]");
    model.param("par3").descr("Dt_transient", "\u77ac\u6001\u65f6\u95f4");
    model.param("par3").set("Dt_tot", "2*Dt+Dt_transient");
    model.param("par3").descr("Dt_tot", "\u4eff\u771f\u603b\u65f6\u95f4");

    model.component("comp1").func().create("tri1", "Triangle");
    model.component("comp1").func("tri1").label("\u51b2\u5934\u4f4d\u79fb");
    model.component("comp1").func("tri1").set("lower", "Dt_transient");
    model.component("comp1").func("tri1").set("upper", "Dt_tot");
    model.component("comp1").func("tri1").set("amplitude", "wmax");
    model.component("comp1").func("tri1").set("smooth", "20[s]");

    model.component("comp1").physics("solid").feature("rd2").create("pdr1", "PrescribedDispRot", -1);
    model.component("comp1").physics("solid").feature("rd2").feature("pdr1").set("W0", "tri1(t)");
    model.component("comp1").physics("solid").feature("rd2").feature("pdr1").set("WeakConstraints", true);
    model.component("comp1").physics("solid").prop("StructuralTransientBehavior")
         .set("StructuralTransientBehavior", "Quasistatic");
    model.component("comp1").physics("solid").feature("dcnt1").set("E_char", "solid.Eequ*(1+betav1+betav2_i)");
    model.component("comp1").physics("solid").feature("dcnt1").create("fric1", "Friction", 1);
    model.component("comp1").physics("solid").feature("dcnt1").feature("fric1").set("mu_fric", 0.05);
    model.component("comp1").physics("solid").feature("dcnt1").feature("fric1")
         .set("penaltyCtrlFrictionPenalty", "ManualTuning");
    model.component("comp1").physics("solid").feature("dcnt1").feature("fric1").set("ft_penalty", 0.1);

    model.param().create("par4");
    model.param("par4").label("\u7f51\u683c\u53c2\u6570");
    model.param("par4").set("w_mesh", "D/2/50");
    model.param("par4").descr("w_mesh", "\u7f51\u683c\u5355\u5143\u5bbd\u5ea6");
    model.param("par4").set("th_mesh", "th/8");
    model.param("par4").descr("th_mesh", "\u7f51\u683c\u5355\u5143\u9ad8\u5ea6");

    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("r5").set("size", new String[]{"w_mesh*8", "th_mesh*2"});
    model.component("comp1").geom("geom1").feature("r5").set("pos", new String[]{"w_mesh*28", "th-th_mesh*2"});
    model.component("comp1").geom("geom1").run("r5");
    model.component("comp1").geom("geom1").create("pard1", "PartitionDomains");
    model.component("comp1").geom("geom1").feature("pard1").selection("domain").set("r1", 1);
    model.component("comp1").geom("geom1").feature("pard1").set("partitionwith", "objects");
    model.component("comp1").geom("geom1").feature("pard1").selection("object").set("r5");
    model.component("comp1").geom("geom1").feature("pard1").set("keepobject", false);
    model.component("comp1").geom("geom1").run("pard1");
    model.component("comp1").geom("geom1").create("r6", "Rectangle");
    model.component("comp1").geom("geom1").feature("r6").set("size", new String[]{"D/2", "th_mesh*2"});
    model.component("comp1").geom("geom1").feature("r6").set("pos", new String[]{"0", "th-th_mesh*2"});
    model.component("comp1").geom("geom1").run("r6");
    model.component("comp1").geom("geom1").feature().duplicate("pard2", "pard1");
    model.component("comp1").geom("geom1").feature("pard2").selection("domain").set("pard1", 1);
    model.component("comp1").geom("geom1").feature("pard2").selection("object").set("r6");
    model.component("comp1").geom("geom1").run("pard2");
    model.component("comp1").geom("geom1").runPre("mce1");
    model.component("comp1").geom("geom1").feature("mce1").selection("input").set("fin", 4, 10, 12, 13, 15, 16);
    model.component("comp1").geom("geom1").run();

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(7);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(26);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 8);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(25);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 2);
    model.component("comp1").mesh("mesh1").run("map1");
    model.component("comp1").mesh("mesh1").create("conv1", "Convert");
    model.component("comp1").mesh("mesh1").feature("conv1").set("splitmethod", "center");
    model.component("comp1").mesh("mesh1").feature("conv1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("conv1").selection().set(7);
    model.component("comp1").mesh("mesh1").run("conv1");
    model.component("comp1").mesh("mesh1").create("map2", "Map");
    model.component("comp1").mesh("mesh1").feature("map2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map2").selection().set(2, 6, 8);
    model.component("comp1").mesh("mesh1").feature("map2").set("smoothcontrol", false);
    model.component("comp1").mesh("mesh1").feature("map2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis1").selection().set(5);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis1").set("numelem", 50);
    model.component("comp1").mesh("mesh1").feature("map2").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis2").selection().set(4);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis2").set("numelem", 6);
    model.component("comp1").mesh("mesh1").feature("map2").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis3").selection().set(19);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis3").set("numelem", 2);
    model.component("comp1").mesh("mesh1").run("map2");
    model.component("comp1").mesh("mesh1").create("map3", "Map");
    model.component("comp1").mesh("mesh1").feature("map3").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map3").selection().set(1, 3);
    model.component("comp1").mesh("mesh1").feature("map3").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map3").feature("dis1").selection().set(9, 18);
    model.component("comp1").mesh("mesh1").feature("map3").feature("dis1").set("numelem", 1);
    model.component("comp1").mesh("mesh1").run("map3");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").set("smoothcontrol", false);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 3);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").feature("time").set("tlist", "range(0,2,Dt_tot)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v1").feature("comp1_solid_hmm1_pvp1_evp1").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_solid_hmm1_pvp1_evp1").set("scaleval", 3);
    model.sol("sol1").feature("v1").feature("comp1_solid_hmm1_pvp1_evp2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_solid_hmm1_pvp1_evp2").set("scaleval", 3);
    model.sol("sol1").feature("v1").feature("comp1_solid_hmm1_pvp1_evpe1").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_solid_hmm1_pvp1_evpe1").set("scaleval", 3);
    model.sol("sol1").feature("v1").feature("comp1_solid_hmm1_pvp1_evpe2").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_solid_hmm1_pvp1_evpe2").set("scaleval", 3);
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "4[mm]");
    model.sol("sol1").feature("v1").feature("comp1_solid_rd_disp").set("scaleval", "4[mm]");
    model.sol("sol1").feature("v1").feature("comp1_solid_rd2_RFz").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_solid_rd2_RFz").set("scaleval", 100);
    model.sol("sol1").feature("v1").feature("comp1_solid_Wvp").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_solid_Wvp").set("scaleval", "1.0E7");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").feature("dDef").set("linsolver", "pardiso");
    model.sol("sol1").feature("t1").feature("fc1").set("minstep", 0.3);
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 20);

    model.study("std1").createAutoSequences("sol");
    model.study("std1").createAutoSequences("jobs");

    model.sol("sol1").runFromTo("st1", "v1");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").run();
    model.result("pg1").label("\u4f4d\u79fb\u5927\u5c0f");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u805a\u5408\u7269");
    model.result("pg1").feature("surf1").create("sel1", "Selection");
    model.result("pg1").feature("surf1").feature("sel1").selection().set(2);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def1").set("scale", 1);
    model.result("pg1").run();
    model.result("pg1").create("surf2", "Surface");
    model.result("pg1").feature("surf2").label("\u51b2\u5934\u548c\u5939\u5177");
    model.result("pg1").feature("surf2").set("titletype", "none");
    model.result("pg1").feature("surf2").set("expr", "1");
    model.result("pg1").feature("surf2").set("coloring", "uniform");
    model.result("pg1").feature("surf2").set("color", "gray");
    model.result("pg1").feature("surf2").create("sel1", "Selection");
    model.result("pg1").feature("surf2").feature("sel1").selection().set(1, 3, 4);
    model.result("pg1").run();
    model.result("pg1").feature("surf2").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").feature("def1").set("scaleactive", true);
    model.result("pg1").feature("surf2").feature("def1").set("scale", 1);

    model.sol("sol1").feature("t1").feature("fc1").set("plot", true);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u51b2\u5934\u529b");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u51b2\u5934\u63a5\u89e6\u529b");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "\u51b2\u5934\u4f4d\u79fb (mm)");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u51b2\u5934\u529b (N)");
    model.result("pg2").set("manualgrid", true);
    model.result("pg2").set("xspacing", 0.5);
    model.result("pg2").set("yspacing", 20);
    model.result("pg2").set("legendpos", "upperleft");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").setIndex("expr", "solid.rd2.RFz", 0);
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").feature("glob1").set("xdataexpr", "tri1(t)");
    model.result("pg2").feature("glob1").set("linewidth", 2);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").label("\u53c2\u8003\u8d44\u6599");
    model.result().table("tbl1").importData("small_punch_test_numerical.txt");
    model.result("pg2").run();
    model.result("pg2").create("tblp1", "Table");
    model.result("pg2").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg2").feature("tblp1").set("linewidth", "preference");
    model.result("pg2").feature("tblp1").label("\u53c2\u8003\u8d44\u6599");
    model.result("pg2").feature("tblp1").set("linestyle", "none");
    model.result("pg2").feature("tblp1").set("linemarker", "circle");
    model.result("pg2").feature("tblp1").set("legend", true);
    model.result("pg2").feature("tblp1").set("autoplotlabel", true);
    model.result("pg2").feature("tblp1").set("autoheaders", false);
    model.result("pg2").run();
    model.result("pg2").feature("glob1").label("COMSOL");
    model.result("pg2").feature("glob1").set("autoplotlabel", true);
    model.result("pg2").feature("glob1").set("autosolution", false);
    model.result("pg2").feature("glob1").set("autodescr", false);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u8017\u6563\u80fd");
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u8017\u6563\u80fd");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").set("expr", new String[]{"solid.Wvp_tot"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"\u603b\u9ecf\u5851\u6027\u8017\u6563"});
    model.result("pg3").feature("glob1").set("unit", new String[]{"J"});
    model.result("pg3").feature("glob1").set("xdata", "expr");
    model.result("pg3").feature("glob1").set("xdataexpr", "tri1(t)");
    model.result("pg3").feature("glob1").set("linewidth", 2);
    model.result("pg3").feature("glob1").set("legend", false);
    model.result("pg3").run();
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").label("\u4e8c\u7ef4\u65cb\u8f6c\uff1a\u805a\u5408\u7269");
    model.result().dataset("rev1").set("startangle", -60);
    model.result().dataset("rev1").set("revangle", 270);
    model.result().dataset("rev1").selection().geom("geom1", 2);
    model.result().dataset("rev1").selection().geom("geom1", 2);
    model.result().dataset("rev1").selection().set(2);
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("\u7b2c\u4e00\u4e3b\u5e94\u53d8");
    model.result("pg4").set("edges", false);
    model.result("pg4").set("titletype", "label");
    model.result("pg4").create("vol1", "Volume");
    model.result("pg4").feature("vol1").set("expr", "solid.ep1");
    model.result("pg4").feature("vol1").set("colortable", "Prism");
    model.result("pg4").feature("vol1").label("\u805a\u5408\u7269");
    model.result("pg4").feature("vol1").create("def1", "Deform");
    model.result("pg4").feature("vol1").feature("def1").set("revcoordsys", "cylindrical");
    model.result("pg4").run();
    model.result("pg4").feature("vol1").feature("def1").set("scaleactive", true);
    model.result("pg4").feature("vol1").feature("def1").set("scale", 1);
    model.result().dataset().duplicate("rev2", "rev1");
    model.result().dataset("rev2").label("\u4e8c\u7ef4\u65cb\u8f6c\uff1a\u51b2\u5934");
    model.result().dataset("rev2").set("startangle", 0);
    model.result().dataset("rev2").set("revangle", 360);
    model.result().dataset("rev2").selection().geom("geom1", 2);
    model.result().dataset("rev2").selection().set(1);
    model.result("pg4").run();
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").label("\u51b2\u5934");
    model.result("pg4").feature("surf1").set("data", "rev2");
    model.result("pg4").feature("surf1").set("solutionparams", "parent");
    model.result("pg4").feature("surf1").set("expr", "1");
    model.result("pg4").feature("surf1").set("coloring", "uniform");
    model.result("pg4").feature("surf1").set("color", "gray");
    model.result("pg4").feature("surf1").create("def1", "Deform");
    model.result("pg4").feature("surf1").feature("def1").set("revcoordsys", "cylindrical");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg4").feature("surf1").feature("def1").set("scale", 1);
    model.result().dataset().duplicate("rev3", "rev2");
    model.result().dataset("rev3").label("\u4e8c\u7ef4\u65cb\u8f6c\uff1a\u5939\u5177");
    model.result().dataset("rev3").set("startangle", -60);
    model.result().dataset("rev3").set("revangle", 270);
    model.result().dataset("rev3").selection().geom("geom1", 2);
    model.result().dataset("rev3").selection().set(3, 4);
    model.result("pg4").run();
    model.result("pg4").create("vol2", "Volume");
    model.result("pg4").feature("vol2").label("\u5939\u5177");
    model.result("pg4").feature("vol2").set("data", "rev3");
    model.result("pg4").feature("vol2").set("solutionparams", "parent");
    model.result("pg4").feature("vol2").set("expr", "1");
    model.result("pg4").feature("vol2").set("coloring", "uniform");
    model.result("pg4").feature("vol2").set("color", "gray");
    model.result("pg4").feature("vol2").create("mtrl1", "MaterialAppearance");
    model.result("pg4").run();
    model.result("pg4").feature("vol2").feature("mtrl1").set("appearance", "custom");
    model.result("pg4").feature("vol2").feature("mtrl1").set("family", "custom");
    model.result("pg4").feature("vol2").feature("mtrl1").set("noisefreq", 1000);
    model.result("pg4").run();

    model.view("view2").set("showgrid", true);

    model.title("\u8d85\u9ad8\u5206\u5b50\u91cf\u805a\u4e59\u70ef\u7684\u5c0f\u51b2\u5b54\u8bd5\u9a8c");

    model
         .description("\u8d85\u9ad8\u5206\u5b50\u91cf\u805a\u4e59\u70ef (UHMWPE) \u662f\u819d\u5173\u8282\u548c\u9acb\u5173\u8282\u7f6e\u6362\u4e2d\u5e38\u7528\u7684\u4e00\u79cd\u6750\u6599\u3002\u201c\u5c0f\u51b2\u5b54\u8bd5\u9a8c\u201d\u65e8\u5728\u4f7f\u7528\u6781\u5c0f\u7684\u6837\u54c1\u6765\u8bc4\u4f30\u529b\u5b66\u5c5e\u6027\uff0c\u8fd9\u4e9b\u6837\u54c1\u56e0\u5176\u4f53\u79ef\u5fae\u5c0f\u800c\u80fd\u591f\u76f4\u63a5\u4ece\u4f53\u5185\u53d6\u51fa\u4ee5\u4f9b\u5206\u6790\u3002\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u201c\u975e\u7ebf\u6027\u7ed3\u6784\u6750\u6599\u6a21\u5757\u201d\u7684\u201c\u805a\u5408\u7269\u9ecf\u5851\u6027\u201d\u7279\u5f81\u4e2d\u7684 Bergstrom-Bischoff \u6750\u6599\u6a21\u578b\u3002");

    return model;
  }

  public static Model run2(Model model) {

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("small_punch_test.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
