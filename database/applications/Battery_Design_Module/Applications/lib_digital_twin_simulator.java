/*
 * lib_digital_twin_simulator.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:42 by COMSOL 6.3.0.290. */
public class lib_digital_twin_simulator {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Battery_Design_Module\\Applications");

    model.component().create("comp1", true);

    model.component("comp1").physics().create("lb", "LumpedBattery");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/lb", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("SOC_min", "0.2", "Minimum SOC allowed before charge");
    model.param().set("E_offset", "0.3[V]", "Offset potential");
    model.param().set("alpha", "0.35", "Transfer coefficient");
    model.param().set("J0", "1.26", "Inverse dimensionless charge exchange current");
    model.param().set("N_series", "9", "Battery cells in series");
    model.param().set("tau", "1200[s]", "Diffusion time constant");
    model.param().set("eta_IR_1C", "20[mV]", "Ohmic overpotential at 1C");
    model.param().set("Q_cell_fresh", "14400[C]", "Fresh battery cell capacity");
    model.param().set("T_ref", "293.15[K]", "Reference temperature");
    model.param().set("T", "298.15[K]", "Temperature");
    model.param().set("V_min", "3.2[V]", "Minimum voltage");
    model.param().set("H", "0.05", "Cycling capacity loss factor");
    model.param().set("V_max", "4.15[V]", "Maximum voltage");
    model.param().set("N_parallel", "2", "Battery cells in parallel");
    model.param().set("tau_loss", "2.5[a]", "Calendar aging time constant");
    model.param().set("G", "50", "Decelerating aging factor");
    model.param().set("charge_factor", "50", "Charge factor");
    model.param().set("Q_cell0", "int_Q_cell(1200)[C]", "Battery cell capacity");
    model.param().set("SOC_0", "int_SOC_0(1200)", "Initial state of charge of battery");

    model.func().create("int1", "Interpolation");
    model.func("int1").set("funcname", "int_Q_cell");
    model.func("int1").setIndex("table", 0, 0, 0);
    model.func("int1").setIndex("table", 14400, 0, 1);
    model.func("int1").setIndex("table", 1200, 1, 0);
    model.func("int1").setIndex("table", 14400, 1, 1);
    model.func("int1").setIndex("argunit", "s", 0);
    model.func().create("int2", "Interpolation");
    model.func("int2").set("funcname", "int_SOC_0");
    model.func("int2").setIndex("table", 0, 0, 0);
    model.func("int2").setIndex("table", 1, 0, 1);
    model.func("int2").setIndex("table", 1200, 1, 0);
    model.func("int2").setIndex("table", 1, 1, 1);
    model.func("int2").setIndex("argunit", "s", 0);

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("I_App", "C_app(time_usenrest+300*rn_random1)*Q_cell_fresh/3600[s]*rn_random2", "Repeated 600 s drive cycle");
    model.component("comp1").variable("var1").set("time_usenrest", "max(wv1(t/1[s]),0)", "Time of usage and rest");
    model.component("comp1").variable("var1").set("E_battery", "comp1.lb.E_cell*N_series", "Battery voltage");
    model.component("comp1").variable("var1").set("I_battery", "I_App*N_parallel*(1-Charge)", "Battery current");
    model.component("comp1").variable("var1")
         .set("SOC_trigger", "if(wv1(t/1[s])<0,1,0)", "SOC indicator trigger (relaxed battery cell after use)");
    model.component("comp1").variable("var1")
         .set("SOC_indicator", "E_OCP_inv(lb.E_cell)*(SOC_trigger>0)", "Battery SOC after each use");
    model.component("comp1").variable("var1")
         .set("E_cell_indicator", "min(lb.E_cell*(SOC_trigger>0)*N_series,E_OCP(SOC_0)*N_series)", "Battery voltage after each use");
    model.component("comp1").variable("var1")
         .set("Charge_trigger", "if(SOC_indicator>0&&SOC_indicator<SOC_min,1,0)", "Event reinitialization expression for charge control");
    model.component("comp1").variable("var1")
         .set("rn_random1", "if(t>20 && t<590, rn1(SOC_0),0)", "Random contributor");
    model.component("comp1").variable("var1").set("rn_random2", "if(t>20 && t<590, rn2(SOC_0),0)");

    model.component("comp1").func().create("int3", "Interpolation");
    model.component("comp1").func("int3").set("funcname", "E_OCP");
    model.component("comp1").func("int3")
         .set("table", new String[][]{{"0", "1.3104190837885064"}, 
         {"0.01", "2.2611069048882873"}, 
         {"0.02", "2.8334213300309643"}, 
         {"0.030000000000000002", "3.1844362423678003"}, 
         {"0.04", "3.393016295869783"}, 
         {"0.05", "3.510875145406253"}, 
         {"0.060000000000000005", "3.5869463584273378"}, 
         {"0.07", "3.6264514929037994"}, 
         {"0.08", "3.6557565420023983"}, 
         {"0.09000000000000001", "3.6850615911009976"}, 
         {"0.1", "3.6994551120045407"}, 
         {"0.11", "3.7113345164250635"}, 
         {"0.12000000000000001", "3.7232139208455868"}, 
         {"0.13", "3.7350933252661096"}, 
         {"0.14", "3.746972729686633"}, 
         {"0.15", "3.7588394760123167"}, 
         {"0.16", "3.7669805471052284"}, 
         {"0.17", "3.7751216181981406"}, 
         {"0.18000000000000002", "3.7832626892910524"}, 
         {"0.19", "3.7914037603839645"}, 
         {"0.2", "3.7995448314768763"}, 
         {"0.21000000000000002", "3.8076859025697884"}, 
         {"0.22", "3.8158269736627"}, 
         {"0.23", "3.8239680447556124"}, 
         {"0.24000000000000002", "3.832109115848524"}, 
         {"0.25", "3.8402384951548894"}, 
         {"0.26", "3.844926614135922"}, 
         {"0.27", "3.8496147331169555"}, 
         {"0.28", "3.8543028520979887"}, 
         {"0.29000000000000004", "3.8589909710790216"}, 
         {"0.3", "3.863679090060055"}, 
         {"0.31", "3.8683672090410877"}, 
         {"0.32", "3.873055328022121"}, 
         {"0.33", "3.877743447003154"}, 
         {"0.34", "3.882431565984187"}, 
         {"0.35000000000000003", "3.8871182387192977"}, 
         {"0.36000000000000004", "3.891379235814907"}, 
         {"0.37", "3.8956402329105164"}, 
         {"0.38", "3.900108284697505"}, 
         {"0.39", "3.9048921849677303"}, 
         {"0.4", "3.910004858946812"}, 
         {"0.41000000000000003", "3.9155565350495363"}, 
         {"0.42000000000000004", "3.9213824207219656"}, 
         {"0.43", "3.927676048105941"}, 
         {"0.44", "3.9340438371441717"}, 
         {"0.45", "3.94051108662678"}, 
         {"0.46", "3.9470234377800457"}, 
         {"0.47000000000000003", "3.9533348394394214"}, 
         {"0.48000000000000004", "3.9592735197022777"}, 
         {"0.49000000000000005", "3.9650124251831564"}, 
         {"0.5", "3.970366661250179"}, 
         {"0.51", "3.975134457606617"}, 
         {"0.52", "3.979408469784772"}, 
         {"0.53", "3.9832924969139087"}, 
         {"0.54", "3.986400783870235"}, 
         {"0.55", "3.989209391104789"}, 
         {"0.56", "3.9920179983393425"}, 
         {"0.5700000000000001", "3.994805300205646"}, 
         {"0.5800000000000001", "3.9971563232603926"}, 
         {"0.5900000000000001", "3.9995073463151387"}, 
         {"0.6", "4.001858369369885"}, 
         {"0.61", "4.004209392424632"}, 
         {"0.62", "4.006560415479378"}, 
         {"0.63", "4.008911438534124"}, 
         {"0.64", "4.011262461588871"}, 
         {"0.65", "4.014107842011973"}, 
         {"0.66", "4.017168121841405"}, 
         {"0.67", "4.020228401670838"}, 
         {"0.68", "4.02328868150027"}, 
         {"0.6900000000000001", "4.0263489613297025"}, 
         {"0.7000000000000001", "4.029409241159136"}, 
         {"0.7100000000000001", "4.032469520988568"}, 
         {"0.7200000000000001", "4.03470071596249"}, 
         {"0.73", "4.035374828848886"}, 
         {"0.74", "4.036048941735283"}, 
         {"0.75", "4.036723054621679"}, 
         {"0.76", "4.037397167508075"}, 
         {"0.77", "4.038071280394472"}, 
         {"0.78", "4.038745393280867"}, 
         {"0.79", "4.039419506167263"}, 
         {"0.8", "4.039965701342635"}, 
         {"0.81", "4.040511627548762"}, 
         {"0.8200000000000001", "4.041057553754889"}, 
         {"0.8300000000000001", "4.041603479961017"}, 
         {"0.8400000000000001", "4.042149406167145"}, 
         {"0.8500000000000001", "4.042695332373272"}, 
         {"0.86", "4.0432412585794"}, 
         {"0.87", "4.04546697590993"}, 
         {"0.88", "4.048603777764223"}, 
         {"0.89", "4.0517405796185155"}, 
         {"0.9", "4.054877381472807"}, 
         {"0.91", "4.0580141833271"}, 
         {"0.92", "4.061150985181393"}, 
         {"0.93", "4.064287787035685"}, 
         {"0.9400000000000001", "4.069434668295399"}, 
         {"0.9500000000000001", "4.079298736570241"}, 
         {"0.9600000000000001", "4.091601063364331"}, 
         {"0.9700000000000001", "4.110991916734893"}, 
         {"0.9800000000000001", "4.13395738036637"}, 
         {"0.9900000000000001", "4.167078533171239"}, 
         {"1", "4.204442586248173"}});
    model.component("comp1").func("int3").setIndex("fununit", "V", 0);
    model.component("comp1").func("int3").setIndex("argunit", 1, 0);
    model.component("comp1").func("int3").set("defineinv", true);
    model.component("comp1").func("int3").set("funcinvname", "E_OCP_inv");
    model.component("comp1").func().create("int4", "Interpolation");
    model.component("comp1").func("int4").set("funcname", "C_app");
    model.component("comp1").func("int4")
         .set("table", new String[][]{{"0", "0"}, 
         {"1", "0"}, 
         {"2", "0"}, 
         {"3", "-1.5"}, 
         {"4", "-2"}, 
         {"5", "-1.8"}, 
         {"6", "-1.2"}, 
         {"7", "-1"}, 
         {"8", "-1.2"}, 
         {"9", "-0.8"}, 
         {"10", "-0.8"}, 
         {"11", "-1"}, 
         {"12", "-1"}, 
         {"13", "-1"}, 
         {"14", "-0.9"}, 
         {"15", "-0.9"}, 
         {"16", "-0.5"}, 
         {"17", "-0.5"}, 
         {"18", "-0.4"}, 
         {"19", "-0.4"}, 
         {"20", "-0.4"}, 
         {"21", "-0.2"}, 
         {"22", "-0.2"}, 
         {"23", "0"}, 
         {"24", "0"}, 
         {"25", "0"}, 
         {"26", "0"}, 
         {"27", "-0.05"}, 
         {"28", "-0.05"}, 
         {"29", "-0.5"}, 
         {"30", "-0.5"}, 
         {"31", "-0.5"}, 
         {"32", "-0.2"}, 
         {"33", "-0.2"}, 
         {"34", "-0.2"}, 
         {"35", "-0.15"}, 
         {"36", "0"}, 
         {"37", "0"}, 
         {"38", "0"}, 
         {"39", "0"}, 
         {"40", "0"}, 
         {"41", "-0.4"}, 
         {"42", "-0.5"}, 
         {"43", "-0.8"}, 
         {"44", "-0.8"}, 
         {"45", "-0.5"}, 
         {"46", "-0.5"}, 
         {"47", "-0.5"}, 
         {"48", "-0.3"}, 
         {"49", "-0.3"}, 
         {"50", "-0.3"}, 
         {"51", "0"}, 
         {"52", "0"}, 
         {"53", "0"}, 
         {"54", "0"}, 
         {"55", "0"}, 
         {"56", "0"}, 
         {"57", "0"}, 
         {"58", "0"}, 
         {"59", "0"}, 
         {"60", "0"}, 
         {"61", "0"}, 
         {"62", "0"}, 
         {"63", "-0.2"}, 
         {"64", "-0.2"}, 
         {"65", "-0.2"}, 
         {"66", "-0.2"}, 
         {"67", "0.025"}, 
         {"68", "0.025"}, 
         {"69", "0.025"}, 
         {"70", "0.025"}, 
         {"71", "0.04"}, 
         {"72", "0.04"}, 
         {"73", "0.05"}, 
         {"74", "0.05"}, 
         {"75", "0.05"}, 
         {"76", "0.025"}, 
         {"77", "0.025"}, 
         {"78", "0.025"}, 
         {"79", "0.025"}, 
         {"80", "0.01"}, 
         {"81", "0.0025"}, 
         {"82", "0.0025"}, 
         {"83", "-0.2"}, 
         {"84", "-0.2"}, 
         {"85", "-0.2"}, 
         {"86", "-0.2"}, 
         {"87", "-0.25"}, 
         {"88", "-0.5"}, 
         {"89", "-0.5"}, 
         {"90", "-0.5"}, 
         {"91", "-0.5"}, 
         {"92", "-0.05"}, 
         {"93", "-0.05"}, 
         {"94", "-0.5"}, 
         {"95", "-0.5"}, 
         {"96", "-0.5"}, 
         {"97", "-0.2"}, 
         {"98", "-0.2"}, 
         {"99", "-0.2"}, 
         {"100", "-0.15"}, 
         {"101", "0"}, 
         {"102", "0"}, 
         {"103", "0"}, 
         {"104", "0"}, 
         {"105", "0"}, 
         {"106", "-0.4"}, 
         {"107", "-0.5"}, 
         {"108", "-0.8"}, 
         {"109", "-0.8"}, 
         {"110", "-0.5"}, 
         {"111", "-0.5"}, 
         {"112", "-0.5"}, 
         {"113", "0.075"}, 
         {"114", "0.065"}, 
         {"115", "0.065"}, 
         {"116", "0.06"}, 
         {"117", "0.025"}, 
         {"118", "0.025"}, 
         {"119", "0.01"}, 
         {"120", "0.005"}, 
         {"121", "0.005"}, 
         {"122", "0"}, 
         {"123", "0"}, 
         {"124", "0"}, 
         {"125", "0"}, 
         {"126", "0"}, 
         {"127", "0"}, 
         {"128", "0"}, 
         {"129", "0"}, 
         {"130", "0"}, 
         {"131", "0.04"}, 
         {"132", "0.04"}, 
         {"133", "0.05"}, 
         {"134", "0.05"}, 
         {"135", "0.05"}, 
         {"136", "0.025"}, 
         {"137", "0.025"}, 
         {"138", "0.025"}, 
         {"139", "0.025"}, 
         {"140", "0.01"}, 
         {"141", "0.0025"}, 
         {"142", "0"}, 
         {"143", "0"}, 
         {"144", "0"}, 
         {"145", "0"}, 
         {"146", "0"}, 
         {"147", "0"}, 
         {"148", "0"}, 
         {"149", "0"}, 
         {"150", "0"}, 
         {"151", "-2"}, 
         {"152", "-2"}, 
         {"153", "-2"}, 
         {"154", "-2"}, 
         {"155", "-1.8"}, 
         {"156", "-1.2"}, 
         {"157", "-1"}, 
         {"158", "-1.2"}, 
         {"159", "-0.8"}, 
         {"160", "-0.8"}, 
         {"161", "-1"}, 
         {"162", "-1"}, 
         {"163", "-1"}, 
         {"164", "-0.9"}, 
         {"165", "-0.9"}, 
         {"166", "-0.5"}, 
         {"167", "-0.5"}, 
         {"168", "-0.4"}, 
         {"169", "-0.4"}, 
         {"170", "-0.4"}, 
         {"171", "-0.2"}, 
         {"172", "-0.2"}, 
         {"173", "0"}, 
         {"174", "0"}, 
         {"175", "0"}, 
         {"176", "0"}, 
         {"177", "-0.05"}, 
         {"178", "-0.05"}, 
         {"179", "-0.5"}, 
         {"180", "-0.5"}, 
         {"181", "-0.5"}, 
         {"182", "-0.2"}, 
         {"183", "-0.2"}, 
         {"184", "-0.2"}, 
         {"185", "-0.15"}, 
         {"186", "0"}, 
         {"187", "0"}, 
         {"188", "0"}, 
         {"189", "0"}, 
         {"190", "0"}, 
         {"191", "-0.4"}, 
         {"192", "-0.5"}, 
         {"193", "-0.8"}, 
         {"194", "-0.8"}, 
         {"195", "-0.5"}, 
         {"196", "-0.5"}, 
         {"197", "-0.5"}, 
         {"198", "-0.3"}, 
         {"199", "-0.3"}, 
         {"200", "-0.3"}, 
         {"201", "0"}, 
         {"202", "0"}, 
         {"203", "0"}, 
         {"204", "0"}, 
         {"205", "0"}, 
         {"206", "0.04"}, 
         {"207", "0.04"}, 
         {"208", "0.05"}, 
         {"209", "0.05"}, 
         {"210", "0.05"}, 
         {"211", "0.025"}, 
         {"212", "0.025"}, 
         {"213", "0.025"}, 
         {"214", "0.025"}, 
         {"215", "0.01"}, 
         {"216", "0.0025"}, 
         {"217", "0.025"}, 
         {"218", "0.025"}, 
         {"219", "0.025"}, 
         {"220", "0.025"}, 
         {"221", "0.04"}, 
         {"222", "0.04"}, 
         {"223", "0.05"}, 
         {"224", "0.05"}, 
         {"225", "0.05"}, 
         {"226", "0.025"}, 
         {"227", "0.025"}, 
         {"228", "0.025"}, 
         {"229", "0.025"}, 
         {"230", "0.01"}, 
         {"231", "0.0025"}, 
         {"232", "0.0025"}, 
         {"233", "-0.2"}, 
         {"234", "-0.2"}, 
         {"235", "-0.2"}, 
         {"236", "-0.2"}, 
         {"237", "-0.25"}, 
         {"238", "-0.5"}, 
         {"239", "-0.5"}, 
         {"240", "-0.5"}, 
         {"241", "-0.5"}, 
         {"242", "0.005"}, 
         {"243", "0.005"}, 
         {"244", "0"}, 
         {"245", "0"}, 
         {"246", "0"}, 
         {"247", "0"}, 
         {"248", "0"}, 
         {"249", "0"}, 
         {"250", "0"}, 
         {"251", "0.025"}, 
         {"252", "0.025"}, 
         {"253", "0.025"}, 
         {"254", "0.025"}, 
         {"255", "-1"}, 
         {"256", "-1"}, 
         {"257", "-1.2"}, 
         {"258", "0"}, 
         {"259", "0"}, 
         {"260", "0.06"}, 
         {"261", "0.075"}, 
         {"262", "0.075"}, 
         {"263", "0.075"}, 
         {"264", "0.065"}, 
         {"265", "0.065"}, 
         {"266", "0.06"}, 
         {"267", "0.025"}, 
         {"268", "0.025"}, 
         {"269", "0.01"}, 
         {"270", "0.005"}, 
         {"271", "0.005"}, 
         {"272", "0"}, 
         {"273", "0"}, 
         {"274", "0"}, 
         {"275", "0"}, 
         {"276", "0"}, 
         {"277", "0"}, 
         {"278", "0"}, 
         {"279", "0"}, 
         {"280", "0"}, 
         {"281", "0"}, 
         {"282", "0"}, 
         {"283", "0"}, 
         {"284", "0"}, 
         {"285", "0"}, 
         {"286", "0.025"}, 
         {"287", "0.025"}, 
         {"288", "0.025"}, 
         {"289", "0.025"}, 
         {"290", "-1"}, 
         {"291", "-1"}, 
         {"292", "-1.2"}, 
         {"293", "0"}, 
         {"294", "0"}, 
         {"295", "0"}, 
         {"296", "0"}, 
         {"297", "0"}, 
         {"298", "0"}, 
         {"299", "0"}, 
         {"300", "0"}, 
         {"301", "-2"}, 
         {"302", "-2"}, 
         {"303", "-2"}, 
         {"304", "-2"}, 
         {"305", "-1.8"}, 
         {"306", "-1.2"}, 
         {"307", "-1"}, 
         {"308", "-1.2"}, 
         {"309", "-0.8"}, 
         {"310", "-0.8"}, 
         {"311", "-1"}, 
         {"312", "-1"}, 
         {"313", "-1"}, 
         {"314", "-0.9"}, 
         {"315", "-0.9"}, 
         {"316", "-0.5"}, 
         {"317", "-0.5"}, 
         {"318", "-0.4"}, 
         {"319", "-0.4"}, 
         {"320", "-0.4"}, 
         {"321", "-0.2"}, 
         {"322", "0"}, 
         {"323", "0"}, 
         {"324", "0"}, 
         {"325", "0"}, 
         {"326", "0.025"}, 
         {"327", "0.025"}, 
         {"328", "0.025"}, 
         {"329", "0.025"}, 
         {"330", "-1"}, 
         {"331", "-1"}, 
         {"332", "-1"}, 
         {"333", "-1.2"}, 
         {"334", "0"}, 
         {"335", "0"}, 
         {"336", "0"}, 
         {"337", "0"}, 
         {"338", "0"}, 
         {"339", "0"}, 
         {"340", "0"}, 
         {"341", "-0.4"}, 
         {"342", "-0.5"}, 
         {"343", "-0.8"}, 
         {"344", "-0.8"}, 
         {"345", "-0.5"}, 
         {"346", "-0.5"}, 
         {"347", "-0.5"}, 
         {"348", "-0.3"}, 
         {"349", "-0.3"}, 
         {"350", "-0.3"}, 
         {"351", "0"}, 
         {"352", "0"}, 
         {"353", "0"}, 
         {"354", "0"}, 
         {"355", "0"}, 
         {"356", "-0.5"}, 
         {"357", "-0.8"}, 
         {"358", "-0.8"}, 
         {"359", "-0.5"}, 
         {"360", "-0.5"}, 
         {"361", "-0.5"}, 
         {"362", "-0.3"}, 
         {"363", "-0.2"}, 
         {"364", "-0.2"}, 
         {"365", "-0.2"}, 
         {"366", "-0.2"}, 
         {"367", "0.025"}, 
         {"368", "0.025"}, 
         {"369", "0.025"}, 
         {"370", "0.025"}, 
         {"371", "0.04"}, 
         {"372", "0.04"}, 
         {"373", "0.05"}, 
         {"374", "0.05"}, 
         {"375", "0.05"}, 
         {"376", "0.025"}, 
         {"377", "0.025"}, 
         {"378", "0.025"}, 
         {"379", "0.025"}, 
         {"380", "0.01"}, 
         {"381", "0.0025"}, 
         {"382", "0.0025"}, 
         {"383", "-0.2"}, 
         {"384", "-0.2"}, 
         {"385", "-0.2"}, 
         {"386", "-0.2"}, 
         {"387", "-0.25"}, 
         {"388", "-0.5"}, 
         {"389", "-0.5"}, 
         {"390", "-0.5"}, 
         {"391", "-0.5"}, 
         {"392", "0.005"}, 
         {"393", "0.005"}, 
         {"394", "0"}, 
         {"395", "0"}, 
         {"396", "0"}, 
         {"397", "0"}, 
         {"398", "0"}, 
         {"399", "0"}, 
         {"400", "0"}, 
         {"401", "0.025"}, 
         {"402", "0.025"}, 
         {"403", "0.025"}, 
         {"404", "0.025"}, 
         {"405", "-1"}, 
         {"406", "-1"}, 
         {"407", "-1.2"}, 
         {"408", "0"}, 
         {"409", "0"}, 
         {"410", "0.06"}, 
         {"411", "0.075"}, 
         {"412", "0.075"}, 
         {"413", "0.075"}, 
         {"414", "0.065"}, 
         {"415", "0.065"}, 
         {"416", "0.06"}, 
         {"417", "0.025"}, 
         {"418", "0.025"}, 
         {"419", "0.01"}, 
         {"420", "0.005"}, 
         {"421", "0.005"}, 
         {"422", "0"}, 
         {"423", "0"}, 
         {"424", "0"}, 
         {"425", "0"}, 
         {"426", "0"}, 
         {"427", "0"}, 
         {"428", "0"}, 
         {"429", "0"}, 
         {"430", "0"}, 
         {"431", "0"}, 
         {"432", "0"}, 
         {"433", "0"}, 
         {"434", "0"}, 
         {"435", "0"}, 
         {"436", "0"}, 
         {"437", "0"}, 
         {"438", "-1"}, 
         {"439", "-1.2"}, 
         {"440", "0"}, 
         {"441", "0"}, 
         {"442", "0.06"}, 
         {"443", "0.075"}, 
         {"444", "0.075"}, 
         {"445", "0.075"}, 
         {"446", "0.065"}, 
         {"447", "0"}, 
         {"448", "0"}, 
         {"449", "0"}, 
         {"450", "0"}, 
         {"451", "-2"}, 
         {"452", "-2"}, 
         {"453", "-2"}, 
         {"454", "-2"}, 
         {"455", "-1.8"}, 
         {"456", "-1.2"}, 
         {"457", "-1"}, 
         {"458", "-1.2"}, 
         {"459", "-0.8"}, 
         {"460", "-0.8"}, 
         {"461", "-1"}, 
         {"462", "-1"}, 
         {"463", "-1"}, 
         {"464", "-0.9"}, 
         {"465", "-0.9"}, 
         {"466", "-0.5"}, 
         {"467", "-0.5"}, 
         {"468", "-0.4"}, 
         {"469", "-0.4"}, 
         {"470", "-0.4"}, 
         {"471", "-0.2"}, 
         {"472", "-0.2"}, 
         {"473", "0"}, 
         {"474", "0"}, 
         {"475", "0"}, 
         {"476", "0"}, 
         {"477", "-0.05"}, 
         {"478", "-0.05"}, 
         {"479", "-0.5"}, 
         {"480", "-0.5"}, 
         {"481", "-0.5"}, 
         {"482", "-0.2"}, 
         {"483", "-0.2"}, 
         {"484", "-0.2"}, 
         {"485", "-0.15"}, 
         {"486", "0"}, 
         {"487", "0"}, 
         {"488", "0"}, 
         {"489", "0"}, 
         {"490", "0"}, 
         {"491", "-0.4"}, 
         {"492", "-0.5"}, 
         {"493", "-0.8"}, 
         {"494", "-0.8"}, 
         {"495", "-0.5"}, 
         {"496", "-0.5"}, 
         {"497", "-0.5"}, 
         {"498", "-0.3"}, 
         {"499", "-0.3"}, 
         {"500", "-0.3"}, 
         {"501", "0"}, 
         {"502", "0"}, 
         {"503", "0"}, 
         {"504", "0"}, 
         {"505", "0"}, 
         {"506", "0"}, 
         {"507", "0"}, 
         {"508", "0"}, 
         {"509", "0"}, 
         {"510", "0"}, 
         {"511", "0"}, 
         {"512", "0"}, 
         {"513", "-0.2"}, 
         {"514", "-0.2"}, 
         {"515", "-0.2"}, 
         {"516", "-0.2"}, 
         {"517", "0.025"}, 
         {"518", "0.025"}, 
         {"519", "0.025"}, 
         {"520", "0.025"}, 
         {"521", "0.04"}, 
         {"522", "0.04"}, 
         {"523", "0.05"}, 
         {"524", "0.05"}, 
         {"525", "0.05"}, 
         {"526", "0.025"}, 
         {"527", "0.025"}, 
         {"528", "0.025"}, 
         {"529", "0.025"}, 
         {"530", "0.01"}, 
         {"531", "0.0025"}, 
         {"532", "0.0025"}, 
         {"533", "-0.2"}, 
         {"534", "-0.2"}, 
         {"535", "-0.2"}, 
         {"536", "-0.2"}, 
         {"537", "-0.25"}, 
         {"538", "-0.5"}, 
         {"539", "-0.5"}, 
         {"540", "-0.5"}, 
         {"541", "-0.5"}, 
         {"542", "0.005"}, 
         {"543", "0.005"}, 
         {"544", "0"}, 
         {"545", "0"}, 
         {"546", "0"}, 
         {"547", "0"}, 
         {"548", "0"}, 
         {"549", "0"}, 
         {"550", "0"}, 
         {"551", "0.025"}, 
         {"552", "0.025"}, 
         {"553", "0.025"}, 
         {"554", "0.025"}, 
         {"555", "-1"}, 
         {"556", "-1"}, 
         {"557", "-1.2"}, 
         {"558", "0"}, 
         {"559", "0"}, 
         {"560", "0.06"}, 
         {"561", "0.075"}, 
         {"562", "0.075"}, 
         {"563", "0.075"}, 
         {"564", "0.065"}, 
         {"565", "0.065"}, 
         {"566", "0.06"}, 
         {"567", "0.025"}, 
         {"568", "0.025"}, 
         {"569", "0.01"}, 
         {"570", "0.005"}, 
         {"571", "0.005"}, 
         {"572", "0"}, 
         {"573", "0"}, 
         {"574", "0"}, 
         {"575", "0"}, 
         {"576", "0"}, 
         {"577", "0"}, 
         {"578", "0"}, 
         {"579", "0"}, 
         {"580", "-0.05"}, 
         {"581", "-0.05"}, 
         {"582", "0.0025"}, 
         {"583", "0.0025"}, 
         {"584", "0.0025"}, 
         {"585", "0"}, 
         {"586", "0"}, 
         {"587", "0"}, 
         {"588", "0"}, 
         {"589", "0"}, 
         {"590", "0"}, 
         {"591", "0"}, 
         {"592", "0"}, 
         {"593", "0"}, 
         {"594", "0"}, 
         {"595", "0"}, 
         {"596", "0"}, 
         {"597", "0"}, 
         {"598", "0"}, 
         {"599", "0"}, 
         {"600", "0"}});
    model.component("comp1").func("int4").setIndex("argunit", "s", 0);
    model.component("comp1").func().create("wv1", "Wave");
    model.component("comp1").func("wv1").set("type", "sawtooth");
    model.component("comp1").func("wv1").set("smoothactive", false);
    model.component("comp1").func("wv1").set("period", "381.59*pi");
    model.component("comp1").func("wv1").set("amplitude", "600[s]");
    model.component("comp1").func().create("rn1", "Random");
    model.component("comp1").func("rn1").set("uniformrange", 0.5);
    model.component("comp1").func("rn1").set("seedactive", true);
    model.component("comp1").func("rn1").set("seed", 11111);
    model.component("comp1").func().create("rn2", "Random");
    model.component("comp1").func("rn2").set("mean", 0.9);
    model.component("comp1").func("rn2").set("uniformrange", 0.2);
    model.component("comp1").func("rn2").set("seedactive", true);
    model.component("comp1").func("rn2").set("seed", 22222);

    model.component("comp1").physics("lb").prop("BatterySettings").set("I_app", "I_App");
    model.component("comp1").physics("lb").prop("BatterySettings").set("Q_cell0", "Q_cell0");
    model.component("comp1").physics("lb").prop("BatterySettings").set("SOC_cell0", "SOC_0");
    model.component("comp1").physics("lb").feature("cep1").set("OpenCircuitVoltageInput", "fromdef");
    model.component("comp1").physics("lb").feature("cep1").set("Eocvdef", "int3");
    model.component("comp1").physics("lb").feature("cep1").set("Tref", "T_ref");
    model.component("comp1").physics("lb").feature("vl1").set("minput_temperature", "T");
    model.component("comp1").physics("lb").feature("vl1").set("eta_ir1C", "eta_IR_1C");
    model.component("comp1").physics("lb").feature("vl1").set("J0", "J0");
    model.component("comp1").physics("lb").feature("vl1").set("IncludeConcentrationOverpotential", true);
    model.component("comp1").physics("lb").feature("vl1").set("tau", "tau");
    model.component("comp1").physics("lb").create("cl1", "CapacityLoss", -1);
    model.component("comp1").physics("lb").feature("cl1").set("minput_temperature", "T");
    model.component("comp1").physics("lb").feature("cl1").set("tau_loss", "if(Charge==0,tau_loss,1e12)");
    model.component("comp1").physics("lb").feature("cl1").set("factorE", true);
    model.component("comp1").physics("lb").feature("cl1").set("E_offset", "E_offset*(1-Charge)");
    model.component("comp1").physics("lb").feature("cl1").set("alpha", "alpha*(1-Charge)");
    model.component("comp1").physics("lb").feature("cl1").set("factorI", true);
    model.component("comp1").physics("lb").feature("cl1").set("H", "H*(1-Charge)");
    model.component("comp1").physics("lb").feature("cl1").set("factoraged", true);
    model.component("comp1").physics("lb").feature("cl1").set("G", "G*(1-Charge)");
    model.component("comp1").physics("lb").create("isc1", "ShortCircuit", -1);
    model.component("comp1").physics("lb").feature("isc1")
         .set("G_short", "-charge_factor*Charge*(1-lb.SOC_average)[S]");
    model.component("comp1").physics().create("ev", "Events");

    model.study("std1").feature("time").setSolveFor("/physics/ev", true);

    model.component("comp1").physics("ev").create("expll1", "ExplicitEventList", -1);
    model.component("comp1").physics("ev").feature("expll1").set("variableName", "Charge");
    model.component("comp1").physics("ev").feature("expll1").set("time", new int[]{0, 600, 1200});
    model.component("comp1").physics("ev").feature("expll1")
         .set("reinitializationExpression", new String[]{"Charge_trigger", "Charge_trigger", "Charge_trigger"});

    model.study("std1").setGenPlots(false);
    model.study("std1").feature("time").set("tlist", "range(0,2,600) 1200");
    model.study("std1").createAutoSequences("sol");
    model.study("std1").createAutoSequences("jobs");

    model.sol("sol1").runFromTo("st1", "v1");
    model.sol("sol1").feature("t1").set("maxstepconstraintbdf", "const");
    model.sol("sol1").feature("t1").set("maxstepbdf", 2);

    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").setIndex("expr", "lb.Q_cell", 0);
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").setIndex("expr", "E_OCP_inv(lb.E_cell)", 0);
    model.result().numerical().create("gev3", "EvalGlobal");
    model.result().numerical("gev3").setIndex("expr", "E_battery", 0);
    model.result().numerical().create("gev4", "EvalGlobal");
    model.result().numerical("gev4").setIndex("expr", "I_battery", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table().create("tbl2", "Table");
    model.result().table().create("tbl3", "Table");
    model.result().table().create("tbl4", "Table");
    model.result().table().create("tbl5", "Table");
    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("twoyaxes", true);
    model.result("pg1").set("yseclabelactive", true);
    model.result("pg1").set("legendpos", "lowerright");
    model.result("pg1").create("tblp1", "Table");
    model.result("pg1").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg1").feature("tblp1").set("linewidth", "preference");
    model.result("pg1").feature("tblp1").set("table", "none");
    model.result("pg1").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg1").feature("tblp1").set("legend", true);
    model.result("pg1").feature("tblp1").set("legendmethod", "manual");
    model.result("pg1").feature("tblp1").setIndex("legends", "Voltage", 0);
    model.result("pg1").feature().duplicate("tblp2", "tblp1");
    model.result("pg1").run();
    model.result("pg1").feature("tblp2").set("plotonsecyaxis", true);
    model.result("pg1").feature("tblp2").setIndex("legends", "Current", 0);

    model.title(null);

    model.description("");

    model.label("lib_digital_twin_simulator_embedded.mph");

    model.result("pg1").run();

    model.title("\u7535\u52a8\u6ed1\u677f\u8f66\u7535\u6c60\u7684\u6570\u5b57\u5b6a\u751f\u6a21\u62df\u5668");

    return model;
  }

  public static Model run2(Model model) {

    model
         .description("\u672c App \u4e0e\u201c\u7535\u52a8\u6ed1\u677f\u8f66\u7535\u6c60\u7684\u6570\u5b57\u5b6a\u751f\u5206\u6790\u5668\u201dApp \u4e00\u8d77\u8fd0\u884c\uff0c\u7528\u4e8e\u6a21\u62df\u7535\u52a8\u6ed1\u677f\u8f66\u7684\u4f7f\u7528\uff0c\u5e76\u751f\u6210\u968f\u65f6\u95f4\u53d8\u5316\u7684\u7535\u6c60\u7535\u6d41\u548c\u7535\u538b\u6570\u636e\u3002");

    model.label("lib_digital_twin_simulator.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
